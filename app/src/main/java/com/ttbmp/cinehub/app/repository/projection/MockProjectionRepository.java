package com.ttbmp.cinehub.app.repository.projection;


import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.dto.MovieDto;
import com.ttbmp.cinehub.app.repository.cinema.MockCinemaRepository;
import com.ttbmp.cinehub.app.repository.hall.MockHallRepository;
import com.ttbmp.cinehub.app.repository.movie.MockMovieRepository;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.Movie;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;

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
    public List<Projection> getProjectionList(Cinema cinema, Movie movie, String date) {
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

    @Override
    public List<Projection> getProjectionList(ProjectionistShift shift) {
        List<Projection> result = new ArrayList<>();
        for (Projection projection : projectionList) {
            if (projection.getDate().equals(shift.getDate())
                    && projection.getHall().equals(shift.getHall())
                    && LocalTime.parse(projection.getStartTime()).isAfter(LocalTime.parse(shift.getStart()))
                    && LocalTime.parse(projection.getStartTime()).isBefore(LocalTime.parse(shift.getEnd()))) {
                // TODO: Check projectionist
                result.add(projection);
            }
        }
        return result;
    }
}
