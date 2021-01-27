package com.ttbmp.cinehub.data.local.mock;

import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.entity.Movie;
import com.ttbmp.cinehub.core.entity.Seat;
import com.ttbmp.cinehub.core.repository.SeatRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class MockSeatRepository implements SeatRepository {
    @Override
    public List<Seat> getListSeat(Cinema cinema, Movie movie, LocalDate date, String time) {
        return Collections.emptyList();
    }
}
