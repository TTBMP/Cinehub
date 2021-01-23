package com.ttbmp.cinehub.data.local.mock;

import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.entity.Movie;
import com.ttbmp.cinehub.core.repository.TimeRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class MockTimeRepository implements TimeRepository {
    @Override
    public List<String> getTimeOfProjection(Cinema cinema, Movie movie, LocalDate date) {
        /*Query al db dato un movie ed un cinema trovare gli orari*/
        if (cinema.getName().equals("UCI Cinema")) {
            return Arrays.asList(
                    "10:50",
                    "13:50",
                    "15:50",
                    "19:50",
                    "21:50"
            );
        } else {
            return Arrays.asList(
                    "9:50",
                    "11:50"
            );

        }

    }
}
