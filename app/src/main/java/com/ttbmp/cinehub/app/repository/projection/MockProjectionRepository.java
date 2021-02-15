package com.ttbmp.cinehub.app.repository.projection;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.cinema.CinemaRepository;
import com.ttbmp.cinehub.app.repository.employee.projectionist.ProjectionistRepository;
import com.ttbmp.cinehub.app.repository.hall.HallRepository;
import com.ttbmp.cinehub.app.repository.hall.MockHallRepository;
import com.ttbmp.cinehub.app.repository.movie.MockMovieRepository;
import com.ttbmp.cinehub.app.repository.movie.MovieRepository;
import com.ttbmp.cinehub.app.repository.shift.MockShiftRepository;
import com.ttbmp.cinehub.app.repository.shift.projectionist.MockProjectionistShiftRepository;
import com.ttbmp.cinehub.app.repository.ticket.TicketRepository;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.Movie;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MockProjectionRepository implements ProjectionRepository {

    private static final List<ProjectionData> PROJECTION_DATA_LIST = new ArrayList<>();
    private static int projectionIdCounter = 0;

    static {
        String time;
        time = "16:00";
        for (LocalDate date = LocalDate.now().minusMonths(1); date.isBefore(LocalDate.now().plusMonths(1)); date = date.plusDays(1)) {
            for (MockHallRepository.HallData hallData : MockHallRepository.getHallDataList()) {
                for (MockMovieRepository.MovieData movieData : MockMovieRepository.getMovieDataList()) {
                    if (movieData.getId() % 2 == date.getDayOfMonth() % 2) {
                        LocalDate finalDate = date;
                        List<Integer> shiftIdList = MockShiftRepository.getShiftDataList().stream()
                                .filter(d -> LocalDate.parse(d.getDate()).equals(finalDate)
                                        && LocalTime.parse(time).isAfter(LocalTime.parse(d.getStart()))
                                        && LocalTime.parse(time).isBefore(LocalTime.parse(d.getEnd()))
                                )
                                .map(MockShiftRepository.ShiftData::getId)
                                .collect(Collectors.toList());
                        List<Integer> projectionistShiftIdList = MockProjectionistShiftRepository.getProjectionistShiftDataList().stream()
                                .filter(d -> shiftIdList.contains(d.getShiftId()))
                                .filter(d -> d.getHallId() == hallData.getId())
                                .map(MockProjectionistShiftRepository.ProjectionistShiftData::getShiftId)
                                .collect(Collectors.toList());
                        if (projectionistShiftIdList.isEmpty()) {
                            break;
                        }
                        int projectionistShiftId = projectionistShiftIdList.get(0);
                        String projectionistId = MockShiftRepository.getShiftDataList().stream()
                                .filter(d -> d.getId() == projectionistShiftId)
                                .map(MockShiftRepository.ShiftData::getEmployeeId)
                                .collect(Collectors.toList())
                                .get(0);

                        PROJECTION_DATA_LIST.add(new ProjectionData(
                                projectionIdCounter++,
                                date.toString(),
                                LocalTime.parse("16:00").toString(),
                                movieData.getId(),
                                hallData.getId(),
                                hallData.getCinemaId(),
                                projectionistId
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
    public List<Projection> getProjectionList(Cinema cinema, Movie movie, String date) {
        return PROJECTION_DATA_LIST.stream()
                .filter(d -> d.cinemaId == cinema.getId() && d.movieId == movie.getId() && d.date.equals(date))
                .map(d -> new ProjectionProxy(
                        d.id,
                        d.date,
                        d.startTime,
                        serviceLocator.getService(MovieRepository.class),
                        serviceLocator.getService(HallRepository.class),
                        serviceLocator.getService(CinemaRepository.class),
                        serviceLocator.getService(ProjectionistRepository.class),
                        serviceLocator.getService(TicketRepository.class)
                ))
                .collect(Collectors.toList());
    }


    @Override
    public List<Projection> getProjectionList(String localDate) {
        return PROJECTION_DATA_LIST.stream()
                .filter(d -> d.date.equals(localDate))
                .map(d -> new ProjectionProxy(
                        d.id,
                        d.date,
                        d.startTime,
                        serviceLocator.getService(MovieRepository.class),
                        serviceLocator.getService(HallRepository.class),
                        serviceLocator.getService(CinemaRepository.class),
                        serviceLocator.getService(ProjectionistRepository.class),
                        serviceLocator.getService(TicketRepository.class)
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<Projection> getProjectionList(Movie movie, String date) {
        return PROJECTION_DATA_LIST.stream()
                .filter(d -> d.movieId == movie.getId() && d.date.equals(date))
                .map(d -> new ProjectionProxy(
                        d.id,
                        d.date,
                        d.startTime,
                        serviceLocator.getService(MovieRepository.class),
                        serviceLocator.getService(HallRepository.class),
                        serviceLocator.getService(CinemaRepository.class),
                        serviceLocator.getService(ProjectionistRepository.class),
                        serviceLocator.getService(TicketRepository.class)
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<Projection> getProjectionList(ProjectionistShift shift) {
        return PROJECTION_DATA_LIST.stream()
                .filter(d -> d.date.equals(shift.getDate())
                        && LocalTime.parse(d.startTime).isAfter(LocalTime.parse(shift.getStart()))
                        && LocalTime.parse(d.startTime).isBefore(LocalTime.parse(shift.getEnd()))
                        && d.hallId == shift.getHall().getId()
                        && d.projectionistId.equals(shift.getEmployee().getId())
                )
                .map(d -> new ProjectionProxy(
                        d.id,
                        d.date,
                        d.startTime,
                        serviceLocator.getService(MovieRepository.class),
                        serviceLocator.getService(HallRepository.class),
                        serviceLocator.getService(CinemaRepository.class),
                        serviceLocator.getService(ProjectionistRepository.class),
                        serviceLocator.getService(TicketRepository.class)
                ))
                .collect(Collectors.toList());
    }

    public static class ProjectionData {

        private int id;
        private String date;
        private String startTime;
        private int movieId;
        private int hallId;
        private int cinemaId;
        private String projectionistId;


        public ProjectionData(int id, String date, String startTime, int movieId, int hallId, int cinemaId, String projectionistId) {
            this.id = id;
            this.date = date;
            this.startTime = startTime;
            this.movieId = movieId;
            this.hallId = hallId;
            this.cinemaId = cinemaId;
            this.projectionistId = projectionistId;

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

        public int getCinemaId() {
            return cinemaId;
        }

        public void setCinemaId(int cinemaId) {
            this.cinemaId = cinemaId;
        }

        public String getProjectionistId() {
            return projectionistId;
        }

        public void setProjectionistId(String projectionistId) {
            this.projectionistId = projectionistId;
        }

    }
}
