package com.ttbmp.cinehub.data.local.mock;

import com.ttbmp.cinehub.core.entity.Hall;
import com.ttbmp.cinehub.core.repository.HallRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class MockHallRepository implements HallRepository {


    @Override
    public List<Hall> retrieveAllHall() {
        List<Hall> hallList = new ArrayList<>();
        hallList.add(new Hall(0));
        hallList.add(new Hall(1));
        hallList.add(new Hall(2));
        hallList.add(new Hall(3));
        hallList.add(new Hall(4));
        hallList.add(new Hall(5));
        return hallList;
    }

}
