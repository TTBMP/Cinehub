package com.ttbmp.cinehub.app.repository.projection;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.hall.MockHallRepository;
import com.ttbmp.cinehub.app.repository.movie.MockMovieRepository;
import com.ttbmp.cinehub.app.repository.ticket.MockTicketRepository;
import com.ttbmp.cinehub.app.utilities.repository.MockRepository;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.Movie;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi, Ivan Palmieri
 */
public class MockProjectionRepository extends MockRepository implements ProjectionRepository {

    public static final String ID = "id";
    public static final String DATE = "date";
    public static final String START_TIME = "startTime";
    public static final String MOVIE_ID = "movieId";
    public static final String HALL_ID = "hallId";
    public static final String BASE_PRICE = "basePrice";

    private static final List<Map<String, String>> mockDataList = getMockDataList(MockProjectionRepository.class);
    private static int projectionIdCounter = 1;

    static {
        var hallNumber = MockHallRepository.getMockDataList().size();
        var movieNumber = MockMovieRepository.getMockDataList().size();
        var firstMovieId = MockMovieRepository.getMockDataList().stream()
                .map(m -> Integer.parseInt(m.get(MockMovieRepository.ID)))
                .sorted()
                .findFirst()
                .orElse(15);
        var lastMovieId = firstMovieId + movieNumber - 1;
        var movieId = firstMovieId;
        for (var date = LocalDate.now().minusDays(15); date.isBefore(LocalDate.now().plusDays(46)); date = date.plusDays(1)) {
            if (!date.getDayOfWeek().equals(DayOfWeek.MONDAY) && !date.getDayOfWeek().equals(DayOfWeek.TUESDAY)) {
                for (var time = LocalTime.parse("15:00"); time.isBefore(LocalTime.parse("22:00")); time = time.plusHours(2)) {
                    for (var hallId = 1; hallId < hallNumber + 1; hallId++) {
                        if (date.isBefore(LocalDate.now().plusDays(15))) {
                            if (movieId > (lastMovieId - movieNumber / 2)) {
                                movieId = firstMovieId;
                            }
                        } else {
                            if (movieId < ((lastMovieId - movieNumber / 2) + 1) || movieId > lastMovieId) {
                                movieId = firstMovieId + movieNumber / 2;
                            }
                        }
                        mockDataList.add(Map.of(
                                ID, Integer.toString(projectionIdCounter++),
                                DATE, date.toString(),
                                START_TIME, time.toString(),
                                MOVIE_ID, Integer.toString(movieId++),
                                HALL_ID, Integer.toString(hallId),
                                BASE_PRICE, Long.toString(5L)
                        ));
                    }
                }
            }
        }
    }

    public MockProjectionRepository(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    public static List<Map<String, String>> getMockDataList() {
        return mockDataList;
    }

    @Override
    public Projection getProjection(int id) {
        return mockDataList.stream()
                .filter(m -> m.get(ID).equals(Integer.toString(id)))
                .findAny()
                .map(m -> new ProjectionProxy(
                        getServiceLocator(),
                        Integer.parseInt(m.get(ID)),
                        m.get(DATE),
                        m.get(START_TIME),
                        Long.parseLong(m.get(BASE_PRICE)))
                )
                .orElse(null);
    }

    @Override
    public Projection getProjection(String date, String time, Hall hall) {
        return mockDataList.stream()
                .filter(m -> m.get(DATE).equals(date)
                        && m.get(START_TIME).equals(time)
                        && m.get(HALL_ID).equals(Integer.toString(hall.getId()))
                )
                .findAny()
                .map(m -> new ProjectionProxy(
                        getServiceLocator(),
                        Integer.parseInt(m.get(ID)),
                        m.get(DATE),
                        m.get(START_TIME),
                        Long.parseLong(m.get(BASE_PRICE)))
                )
                .orElse(null);
    }

    @Override
    public Projection getProjection(Ticket ticket) throws RepositoryException {
        return MockTicketRepository.getMockDataList().stream()
                .filter(m -> m.get(MockTicketRepository.ID).equals(Integer.toString(ticket.getId())))
                .map(m -> m.get(MockTicketRepository.PROJECTION_ID))
                .findAny()
                .flatMap(projectionId -> mockDataList.stream()
                        .filter(m -> m.get(ID).equals(projectionId))
                        .findAny()
                        .map(m -> new ProjectionProxy(
                                getServiceLocator(),
                                Integer.parseInt(m.get(ID)),
                                m.get(DATE),
                                m.get(START_TIME),
                                Long.parseLong(m.get(BASE_PRICE)))
                        )
                )
                .orElse(null);
    }

    @Override
    public List<Projection> getProjectionList(ProjectionistShift shift) {
        return mockDataList.stream()
                .filter(m -> m.get(DATE).equals(shift.getDate())
                        && LocalTime.parse(m.get(START_TIME)).isAfter(LocalTime.parse(shift.getStart()))
                        && LocalTime.parse(m.get(START_TIME)).isBefore(LocalTime.parse(shift.getEnd()))
                        && m.get(HALL_ID).equals(Integer.toString(shift.getHall().getId()))
                )
                .map(m -> new ProjectionProxy(
                        getServiceLocator(),
                        Integer.parseInt(m.get(ID)),
                        m.get(DATE),
                        m.get(START_TIME),
                        Long.parseLong(m.get(BASE_PRICE)))
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<Projection> getProjectionList(Cinema cinema, Movie movie, String date) {
        var hallList = MockHallRepository.getMockDataList().stream()
                .filter(m -> m.get(MockHallRepository.CINEMA_ID).equals(Integer.toString(cinema.getId())))
                .map(m -> m.get(HALL_ID))
                .collect(Collectors.toList());
        return mockDataList.stream()
                .filter(m -> m.get(MOVIE_ID).equals(Integer.toString(movie.getId())))
                .filter(m -> m.get(DATE).equals(date))
                .filter(m -> hallList.contains(m.get(HALL_ID)))
                .map(m -> new ProjectionProxy(
                        getServiceLocator(),
                        Integer.parseInt(m.get(ID)),
                        m.get(DATE),
                        m.get(START_TIME),
                        Long.parseLong(m.get(BASE_PRICE)))
                )
                .collect(Collectors.toList());
    }

}
