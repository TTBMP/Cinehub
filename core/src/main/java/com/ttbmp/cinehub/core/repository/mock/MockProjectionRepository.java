package com.ttbmp.cinehub.core.repository.mock;


import com.ttbmp.cinehub.core.dto.CinemaDto;
import com.ttbmp.cinehub.core.dto.MovieDto;
import com.ttbmp.cinehub.core.entity.Hall;
import com.ttbmp.cinehub.core.entity.Movie;
import com.ttbmp.cinehub.core.entity.Projection;
import com.ttbmp.cinehub.core.repository.ProjectionRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Fabio Buracchi, Massimo Mazzetti
 */
public class MockProjectionRepository implements ProjectionRepository {

    private static final List<Projection> projectionList = new ArrayList<>();

    static {
        int j = 0;
        for (Movie movie : MockMovieRepository.getAllMovie()) {
            for (int i = 0; i < 7; i++) {
                if ((i + movie.getId()) % 2 == 0) {
                    for (Hall hall : new MockHallRepository().getAllHall()) {
                        if (j % 2 == 0) {
                            projectionList.add(new Projection(
                                    movie,
                                    new MockCinemaRepository().getCinema(hall),
                                    hall,
                                    LocalTime.now().plusHours(j).withSecond(0).withNano(0).toString(),
                                    LocalDate.now().plusDays(i).toString()));
                        }
                        j++;
                    }
                    j = j - 7;
                }
            }
        }
    }

    @Override
    public List<Projection> getProjectionList(CinemaDto cinema, MovieDto movie, String date) {
        List<Projection> result = new ArrayList<>();
        for (Projection projection : projectionList) {
            if (projection.getCinema().getName().equals(cinema.getName()) &&
                    projection.getMovie().getId() == movie.getId() &&
                    projection.getDate().equals(date)) {
                result.add(projection);
            }
        }
        return result;
    }

    @Override
    public List<Projection> getProjectionList(String localDate) {
        List<Projection> result = new ArrayList<>();
        for (Projection projection : projectionList) {
            if (projection.getDate().equals(localDate)) {
                result.add(projection);
            }
        }
        return result;
    }

    @Override
    public List<Projection> getProjectionList(Movie movie, String date) {
        List<Projection> result = new ArrayList<>();
        for (Projection projection : projectionList) {
            if (projection.getDate().equals(date) && projection.getMovie().getId() == movie.getId()) {
                result.add(projection);
            }
        }
        return result;
    }
}
