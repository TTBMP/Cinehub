package com.ttbmp.cinehub.data.local.mock;

import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.entity.Hall;
import com.ttbmp.cinehub.core.repository.HallRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Massimo Mazzetti
 */

public class MockHallRepository implements HallRepository {

    private final List<Hall> hallList;

    public MockHallRepository() {

        hallList = (Arrays.asList(new Hall("1", new Cinema("Comunale"))
                , new Hall("2", new Cinema("MultiPlex")), new Hall("3", new Cinema("Comunale"))));

    }

    @Override
    public List<Hall> getCinemaHallList(Cinema cinema) {
        return hallList
                .stream()
                .filter(a -> a.getCinema().equals(cinema))
                .collect(Collectors.toList());
    }
}
