package com.ttbmp.cinehub.data.local.mock;

import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.entity.Hall;
import com.ttbmp.cinehub.core.entity.Movie;
import com.ttbmp.cinehub.core.entity.Projection;
import com.ttbmp.cinehub.core.repository.CinemaRepository;
import com.ttbmp.cinehub.core.repository.ProjectionRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Massimo Mazzetti, Ivan Palmieri
 */
public class MockCinemaRepository implements CinemaRepository {


    private static final List<Cinema> cinemaList = new ArrayList<>();

    static {
        cinemaList.add(new Cinema(
                0,
                "Comunale",
                "via recanati 3",
                "Recanati",
                new MockHallRepository().getHallList(0)
        ));
        cinemaList.add(new Cinema(
                1,
                "MultiPlex",
                " via garibaldi 1",
                "Teramo",
                new MockHallRepository().getHallList(1)
        ));
    }

    public Cinema getCinema(int cinemaId) {
        return cinemaList.get(cinemaId);
    }

    @Override
    public List<Cinema> getAllCinema() {
        return cinemaList;
    }


    @Override
    public List<Cinema> getListCinema(Movie movie, String date) {
        ProjectionRepository projectionRepository = new MockProjectionRepository();
        List<Projection> projectionList = projectionRepository.getProjectionList(movie, date);//I recover the projections of a specific film on a specific date
        List<Cinema> result = new ArrayList<>();
        for (Projection projection : projectionList) {
            if (!result.contains(projection.getCinema())) {
                result.add(projection.getCinema());
            }
        }
        return result;

    }

    @Override
    public Cinema getCinema(Hall hall) {
        for (Cinema cinema : cinemaList) {
            for (Hall hallCinema : cinema.getHallList()) {
                if (hallCinema.getId().equals(hall.getId())) {
                    return cinema;
                }
            }
        }
        return null;
    }


}
