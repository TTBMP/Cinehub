package com.ttbmp.cinehub.app.repository.projection;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.employee.projectionist.ProjectionistRepository;
import com.ttbmp.cinehub.app.repository.hall.HallRepository;
import com.ttbmp.cinehub.app.repository.hall.MockHallRepository;
import com.ttbmp.cinehub.app.repository.movie.MockMovieRepository;
import com.ttbmp.cinehub.app.repository.movie.MovieRepository;
import com.ttbmp.cinehub.app.repository.ticket.MockTicketRepository;
import com.ttbmp.cinehub.app.repository.ticket.TicketRepository;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.Movie;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi, Ivan Palmieri
 */
public class MockProjectionRepository implements ProjectionRepository {

    private static final List<ProjectionData> PROJECTION_DATA_LIST = new ArrayList<>();
    private static int projectionIdCounter = 1;

    static {
        var hallNumber = MockHallRepository.getHallDataList().size();
        var movieNumber = MockMovieRepository.getMovieDataList().size();
        var firstMovieId = MockMovieRepository.getMovieDataList().stream()
                .map(MockMovieRepository.MovieData::getId)
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
                        PROJECTION_DATA_LIST.add(new ProjectionData(
                                projectionIdCounter++,
                                date.toString(),
                                time.toString(),
                                movieId++,
                                hallId,
                                5L
                        ));
                    }
                }
            }
        }
    }

    private final ServiceLocator serviceLocator;

    public MockProjectionRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public static List<ProjectionData> getProjectionDataList() {
        return PROJECTION_DATA_LIST;
    }

    @Override
    public Projection getProjection(int id) {
        return PROJECTION_DATA_LIST.stream()
                .filter(d -> d.id == id)
                .findAny()
                .map(d -> new ProjectionProxy(
                        d.id,
                        d.date,
                        d.startTime,
                        serviceLocator.getService(MovieRepository.class),
                        serviceLocator.getService(HallRepository.class),
                        serviceLocator.getService(ProjectionistRepository.class),
                        serviceLocator.getService(TicketRepository.class),
                        d.basePrice
                ))
                .orElse(null);
    }

    @Override
    public Projection getProjection(String date, String time, Hall hall) {
        return PROJECTION_DATA_LIST.stream()
                .filter(d -> d.date.equals(date) && d.startTime.equals(time) && d.hallId == hall.getId())
                .findAny()
                .map(d -> new ProjectionProxy(
                        d.id,
                        d.date,
                        d.startTime,
                        serviceLocator.getService(MovieRepository.class),
                        serviceLocator.getService(HallRepository.class),
                        serviceLocator.getService(ProjectionistRepository.class),
                        serviceLocator.getService(TicketRepository.class),
                        d.basePrice
                ))
                .orElse(null);
    }

    @Override
    public Projection getProjection(Ticket ticket) throws RepositoryException {
        return MockTicketRepository.getTicketDataList().stream()
                .filter(d -> d.getId() == ticket.getId())
                .findAny()
                .flatMap(ticketData -> PROJECTION_DATA_LIST.stream()
                        .filter(d -> ticketData.getProjectionId() == d.id)
                        .findAny()
                        .map(d -> new ProjectionProxy(
                                d.id,
                                d.date,
                                d.startTime,
                                serviceLocator.getService(MovieRepository.class),
                                serviceLocator.getService(HallRepository.class),
                                serviceLocator.getService(ProjectionistRepository.class),
                                serviceLocator.getService(TicketRepository.class),
                                d.basePrice
                        )))
                .orElse(null);
    }

    @Override
    public List<Projection> getProjectionList(ProjectionistShift shift) {
        return PROJECTION_DATA_LIST.stream()
                .filter(d -> d.date.equals(shift.getDate())
                        && LocalTime.parse(d.startTime).isAfter(LocalTime.parse(shift.getStart()))
                        && LocalTime.parse(d.startTime).isBefore(LocalTime.parse(shift.getEnd()))
                        && d.hallId == shift.getHall().getId()
                )
                .map(d -> new ProjectionProxy(
                        d.id,
                        d.date,
                        d.startTime,
                        serviceLocator.getService(MovieRepository.class),
                        serviceLocator.getService(HallRepository.class),
                        serviceLocator.getService(ProjectionistRepository.class),
                        serviceLocator.getService(TicketRepository.class),
                        d.basePrice
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<Projection> getProjectionList(Cinema cinema, Movie movie, String date) {
        var hallList = MockHallRepository.getHallDataList().stream()
                .filter(d -> d.getCinemaId() == cinema.getId())
                .map(MockHallRepository.HallData::getId)
                .collect(Collectors.toList());
        return PROJECTION_DATA_LIST.stream()
                .filter(d -> d.movieId == movie.getId())
                .filter(d -> d.date.equals(date))
                .filter(d -> hallList.contains(d.hallId))
                .map(d -> new ProjectionProxy(
                        d.id,
                        d.date,
                        d.startTime,
                        serviceLocator.getService(MovieRepository.class),
                        serviceLocator.getService(HallRepository.class),
                        serviceLocator.getService(ProjectionistRepository.class),
                        serviceLocator.getService(TicketRepository.class),
                        d.basePrice
                ))
                .collect(Collectors.toList());
    }


    public static class ProjectionData {

        private int id;
        private String date;
        private String startTime;
        private int movieId;
        private int hallId;
        private long basePrice;

        public ProjectionData(int id, String date, String startTime, int movieId, int hallId, long basePrice) {
            this.id = id;
            this.date = date;
            this.startTime = startTime;
            this.movieId = movieId;
            this.hallId = hallId;
            this.basePrice = basePrice;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public int getMovieId() {
            return movieId;
        }

        public void setMovieId(int movieId) {
            this.movieId = movieId;
        }

        public int getHallId() {
            return hallId;
        }

        public void setHallId(int hallId) {
            this.hallId = hallId;
        }

        public long getBasePrice() {
            return basePrice;
        }

        public void setBasePrice(long basePrice) {
            this.basePrice = basePrice;
        }

    }

}
