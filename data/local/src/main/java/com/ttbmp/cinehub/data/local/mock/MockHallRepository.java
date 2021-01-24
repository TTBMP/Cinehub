package com.ttbmp.cinehub.data.local.mock;

import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.entity.Hall;
import com.ttbmp.cinehub.core.entity.Projection;
import com.ttbmp.cinehub.core.repository.HallRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class MockHallRepository implements HallRepository {

    private final List<Hall> hallList = new ArrayList<>();


    @Override
    public List<Hall> retriveHallByCinema(Cinema cinema) {
        return hallList;
    }

    @Override
    public List<Hall> retriveAllHall() {
        return hallList;
    }

    @Override
    public Hall retriveHallByProjection(Projection projection) {
        return projection.getHall();
    }
}
