package com.ttbmp.cinehub.data.local.mock;

import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.repository.CinemaRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class MockCinemaRepository implements CinemaRepository {

    private final List<Cinema> cinemaList = new ArrayList<>();




    @Override
    public List<Cinema> getAllCinemaList() {
        cinemaList.clear();
        this.cinemaList.addAll(Arrays.asList(
                new Cinema("The cinema", "Via ovunque", "Tivoli"),
                new Cinema("UCI cinema", "Via delle vie", "Monterotondo"),
                new Cinema("Cinema Giuseppetti", "Via Nettuno", "Nettuno"),
                new Cinema("Cinema Bous", "Via rossi", "Milano")
        ));
        return cinemaList;
    }


}
