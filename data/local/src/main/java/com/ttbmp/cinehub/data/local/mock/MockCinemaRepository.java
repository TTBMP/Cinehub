package com.ttbmp.cinehub.data.local.mock;

import com.ttbmp.cinehub.core.entity.*;
import com.ttbmp.cinehub.core.repository.CinemaRepository;
import com.ttbmp.cinehub.core.repository.ProjectionRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class MockCinemaRepository implements CinemaRepository {


    private final List<Cinema> cinemaList = new ArrayList<>();



    @Override
    public List<Cinema> getListCinema(List<Projection> projectionList) {
        cinemaList.clear();
        for (Projection projection : projectionList) {
            cinemaList.add(projection.getCinema());
        }
        for (int i = 0; i < cinemaList.size(); i++) {
            for (int j = i + 1; j < cinemaList.size(); j++) {
                if (cinemaList.get(i).getName().equals(cinemaList.get(j).getName())) {
                    cinemaList.remove(j);
                    break;
                }
            }
        }
        return cinemaList;
    }

    @Override
    public List<Cinema> getListCinema(Movie movie, String date) {
        ProjectionRepository projectionRepository = new MockProjectionRepository();
        List<Projection> projectionList = projectionRepository.getProjectionList(movie, date);//I recover the projections of a specific film on a specific date
        return getListCinema(projectionList);
    }


}
