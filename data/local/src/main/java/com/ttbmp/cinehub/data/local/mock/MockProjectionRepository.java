package com.ttbmp.cinehub.data.local.mock;


import com.ttbmp.cinehub.core.dto.CinemaDto;
import com.ttbmp.cinehub.core.dto.MovieDto;
import com.ttbmp.cinehub.core.entity.*;
import com.ttbmp.cinehub.core.repository.ProjectionRepository;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class MockProjectionRepository implements ProjectionRepository {

    private final List<Projection> projectionList = new ArrayList<>();
    private final List<Projection> projectionListByDate = new ArrayList<>();
    private final List<Projection> projectionListByDateAndMovie = new ArrayList<>();
    private final List<Projection> projectionListByDateAndMovieAndCinema = new ArrayList<>();


    @Override
    public List<Projection> getAllProjection() {
        this.projectionList.clear();

        Projection projection = new Projection(
                new Movie("The Dark"),
                new Cinema("Cinema the dark", "Via via", "Netting"),
                new Hall(1),
                "20:14",
                "2021-02-12"
        );
        projection.getHall().setSeatList(new ArrayList<>());
        for (int i = 0; i < 41; i++) {
            projection.getHall().getSeatList().add(new Seat((long) i % 4 + 4, true));
        }


        Projection projection1 = new Projection(
                new Movie("The Dark"),
                new Cinema("Cinema the dark", "Via via", "Netting"),
                new Hall(4),
                "18:00",
                "2021-02-12"
        );
        projection1.getHall().setSeatList(new ArrayList<>());
        for (int i = 0; i < 55; i++) {
            projection1.getHall().getSeatList().add(new Seat((long) i % 4 + 4, true));
        }


        Projection projection2 = new Projection(
                new Movie("Star Wars"),
                new Cinema("Cinema Star", "Via delle vie", "Tirol"),
                new Hall(1),
                "20:20",
                "2021-01-29"
        );
        projection2.getHall().setSeatList(new ArrayList<>());
        for (int i = 0; i < 20; i++) {
            projection2.getHall().getSeatList().add(new Seat((long) i % 4 + 4, true));

        }
        Projection projection3 = new Projection(
                new Movie("Finding Nemo"),
                new Cinema("Cinema Nemo", "Via dell donne", "Roma"),
                new Hall(5),
                "20:00",
                "2021-02-13"
        );
        projection3.getHall().setSeatList(new ArrayList<>());
        for (int i = 0; i < 70; i++) {
            projection3.getHall().getSeatList().add(new Seat((long) i % 4 + 4, true));

        }
        Projection projection4 = new Projection(
                new Movie("Star Wars"),
                new Cinema("Cinema Wars", "Via dell donne", "Roma"),
                new Hall(3),
                "20:00",
                "2021-02-13"
        );
        projection4.getHall().setSeatList(new ArrayList<>());
        for (int i = 0; i < 41; i++) {
            projection4.getHall().getSeatList().add(new Seat((long) i % 4 + 4, true));

        }
        projection.getHall().getSeatList().get(7).setState(false);
        projection1.getHall().getSeatList().get(7).setState(false);
        projection2.getHall().getSeatList().get(7).setState(false);
        projection3.getHall().getSeatList().get(7).setState(false);
        projection4.getHall().getSeatList().get(7).setState(false);

        projectionList.add(projection);
        projectionList.add(projection1);
        projectionList.add(projection2);
        projectionList.add(projection3);
        projectionList.add(projection4);
        return projectionList;
    }


    @Override
    public List<Projection> getProjectionList(CinemaDto cinema, MovieDto movie, String date) {
        projectionListByDateAndMovieAndCinema.clear();
        for (Projection projection : getAllProjection()) {
            if (projection.getCinema().getName().equals(cinema.getName()) &&
                    projection.getMovie().getName().equals(movie.getName()) &&
                    projection.getDate().equals(date)) {
                projectionListByDateAndMovieAndCinema.add(projection);
            }
        }
        return projectionListByDateAndMovieAndCinema;
    }

    @Override
    public List<Projection> getProjectionList(String localDate) {
        projectionListByDate.clear();
        List<Projection> allProjection = getAllProjection();
        for (Projection projection : allProjection) {
                if (projection.getDate().equals(localDate)) {
                    projectionListByDate.add(projection);
                }
            }
        return projectionListByDate;
    }

    @Override
    public List<Projection> getProjectionList(Movie movie, String date) {
        projectionListByDateAndMovie.clear();
        for (Projection projection : getAllProjection()) {
            if (projection.getDate().equals(date) && projection.getMovie().getName().equals(movie.getName())) {
                projectionListByDateAndMovie.add(projection);
            }
        }
        return projectionListByDateAndMovie;
    }
}
