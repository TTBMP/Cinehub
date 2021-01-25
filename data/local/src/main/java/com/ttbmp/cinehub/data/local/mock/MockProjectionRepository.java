package com.ttbmp.cinehub.data.local.mock;

import com.ttbmp.cinehub.core.entity.*;
import com.ttbmp.cinehub.core.repository.ProjectionRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class MockProjectionRepository implements ProjectionRepository {

    private final List<Projection> projectionList = new ArrayList<>();

    @Override
    public List<Projection> getProjectionByHall(Hall hall) {
        return projectionList;
    }

    @Override
    public List<Projection> getProjectionByMovie(Movie movie, String data) {
        projectionList.clear();
        getAllProjection();
        List<Projection> listProjectionByDate = new ArrayList<>();
        List<Projection> listProjectionByMovie = new ArrayList<>();
        for (Projection projection : projectionList) {
            if (projection.getDate().equals(data)) {
                listProjectionByDate.add(projection);
            }
        }
        for (Projection projection : listProjectionByDate) {
            if (projection.getMovie().getName().equals(movie.getName())) {
                listProjectionByMovie.add(projection);
            }
        }
        return listProjectionByMovie;
    }

    @Override
    public List<Projection> getAllProjection() {//Query al db
        this.projectionList.addAll(Arrays.asList(
                new Projection(
                        new Movie("The farnvx1"),
                        new Cinema("CinemaDiProva1", "Via delle vie", "Tivoli"),
                        new Hall(1),
                        "20:20",
                        "2021-02-12"
                ),
                new Projection(
                        new Movie("Metropolis"),
                        new Cinema("CinemaDiProva2", "Via delle donne", "Roma"),
                        new Hall(1),
                        "20:00",
                        "2021-02-13"
                ),
                new Projection(
                        new Movie("The farnvx3"),
                        new Cinema("CinemaDiProva3", "Via della lazio", "Marcellina"),
                        new Hall(1),
                        "20:10",
                        "2021-02-14"
                )
        ));
        Projection projection = new Projection(
                new Movie("The Dark"),
                new Cinema("CinemaDiProva", "Via via", "Nettuno"),
                new Hall(1),
                "20:00",
                "2021-02-12"
        );
        projection.getHall().setSeatList(Arrays.asList(
                new Seat(5L, true),
                new Seat(7L, true),
                new Seat(8L, true),
                new Seat(6L, true),
                new Seat(4L, true),
                new Seat(5L, true),
                new Seat(7L, true),
                new Seat(8L, true),
                new Seat(6L, true),
                new Seat(4L, true),
                new Seat(5L, true),
                new Seat(7L, true),
                new Seat(8L, true),
                new Seat(6L, true),
                new Seat(4L, true),
                new Seat(5L, true),
                new Seat(7L, true),
                new Seat(8L, true),
                new Seat(6L, true),
                new Seat(4L, true),
                new Seat(5L, true),
                new Seat(7L, true),
                new Seat(8L, true),
                new Seat(6L, true),
                new Seat(4L, true),
                new Seat(5L, true),
                new Seat(7L, true),
                new Seat(8L, true),
                new Seat(6L, true),
                new Seat(4L, true),
                new Seat(5L, true),
                new Seat(7L, true),
                new Seat(8L, true),
                new Seat(6L, true),
                new Seat(4L, true),
                new Seat(5L, true),
                new Seat(7L, true),
                new Seat(8L, true),
                new Seat(6L, true),
                new Seat(4L, true),
                new Seat(4L, true)));
        projectionList.add(projection);
        return projectionList;
    }

    @Override
    public List<String> getTimeOfProjectionByMovieEndCinema(Movie movie, Cinema cinema) {
        List<String> timeList = new ArrayList<>();
        projectionList.clear();
        getAllProjection();
        for (Projection projection : projectionList) {
            if (projection.getCinema().getName().equals(cinema.getName()) && projection.getMovie().getName().equals(movie.getName())) {
                timeList.add(projection.getStartTime());
            }
        }
        return timeList;
    }

    @Override
    public Projection getTimeOfProjectionByMovieEndCinemaEndTime(Movie movie, Cinema cinema, String time) {
        projectionList.clear();
        getAllProjection();
        for (Projection projection : projectionList) {
            if (projection.getCinema().getName().equals(cinema.getName()) &&
                    projection.getMovie().getName().equals(movie.getName()) &&
                    projection.getStartTime().equals(time)) {
                return projection;
            }
        }
        return null;
    }
}
