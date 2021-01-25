package com.ttbmp.cinehub.data.local.mock;

import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.entity.Movie;
import com.ttbmp.cinehub.core.repository.TimeRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class MockTimeRepository implements TimeRepository {
    @Override
    public List<String> getTimeOfProjection(Cinema cinema, Movie movie, LocalDate date) {
        return new ArrayList<>();
    }
}
