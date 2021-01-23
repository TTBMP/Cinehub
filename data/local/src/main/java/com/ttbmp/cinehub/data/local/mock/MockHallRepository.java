package com.ttbmp.cinehub.data.local.mock;

import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.entity.Hall;
import com.ttbmp.cinehub.core.repository.HallRepository;
import com.ttbmp.cinehub.core.utilities.result.Result;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MockHallRepository implements HallRepository {

    private final List<Hall> hallList;

    public MockHallRepository() {

        hallList = (Arrays.asList(new Hall("1", new Cinema("pippo"))
                , new Hall("2", new Cinema("fabio")), new Hall("3", new Cinema("pippo"))));

    }

    @Override
    public List<Hall> getCinemaHallList(Cinema cinema) {
        return hallList
                .stream()
                .filter(a -> a.getCinema().equals(cinema))
                .collect(Collectors.toList());
    }
}
