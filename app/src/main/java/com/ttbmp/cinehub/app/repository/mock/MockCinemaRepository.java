package com.ttbmp.cinehub.app.repository.mock;

import com.ttbmp.cinehub.app.repository.CinemaRepository;
import com.ttbmp.cinehub.app.repository.ProjectionRepository;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.Movie;
import com.ttbmp.cinehub.domain.Projection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Fabio Buracchi, Massimo Mazzetti, Ivan Palmieri
 */
public class MockCinemaRepository implements CinemaRepository {

    private static final List<Cinema> cinemaList = new ArrayList<>();

    static {
        cinemaList.add(new Cinema(
                0,
                "Comunale",
                "Recanati", "via recanati 3",
                new MockHallRepository().getHallList(0)
        ));
        cinemaList.add(new Cinema(
                1,
                "MultiPlex",
                "Teramo", "via garibaldi 1",
                new MockHallRepository().getHallList(1)
        ));
    }

    public Cinema getCinema(int cinemaId) {
        return cinemaList.get(cinemaId);
    }

    @Override
    public Cinema getCinema(Hall hall) {
        for (Cinema cinema : cinemaList) {
            for (Hall hallCinema : cinema.getHallList()) {
                if (hallCinema.equals(hall)) {
                    return cinema;
                }
            }
        }
        return null;
    }

    @Override
    public List<Cinema> getAllCinema() {
        return cinemaList;
    }

    @Override
    public List<Cinema> getListCinema(Movie movie, String date) {
        ProjectionRepository projectionRepository = new MockProjectionRepository();
        List<Projection> projectionList = projectionRepository.getProjectionList(movie, date);
        List<Cinema> result = new ArrayList<>();
        for (Projection projection : projectionList) {
            if (!result.contains(projection.getCinema())) {
                result.add(projection.getCinema());
            }
        }
        return result;
    }

}
