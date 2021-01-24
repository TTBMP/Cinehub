package com.ttbmp.cinehub.data.local.mock;

import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.entity.Movie;
import com.ttbmp.cinehub.core.entity.Seat;
import com.ttbmp.cinehub.core.repository.SeatRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class MockSeatRepository implements SeatRepository {
    @Override
    public List<Seat> getListSeat(Cinema cinema, Movie movie, LocalDate date, String time) {
        /*Fa la query al db e dice dato un film in quel cinema, dammi tutti i Posti*/
        return Arrays.asList(
                new Seat(5L, true),
                new Seat(7L, true),
                new Seat(8L, true),
                new Seat(6L, true),
                new Seat(4L, true),
                new Seat(5L, true),
                new Seat(7L, true),
                new Seat(8L, true),
                new Seat(6L, true),
                new Seat(4L, true),
                new Seat(8L, true),
                new Seat(6L, true),
                new Seat(4L, true),
                new Seat(5L, true),
                new Seat(7L, true),
                new Seat(8L, true),
                new Seat(6L, true),
                new Seat(4L, true),
                new Seat(8L, true),
                new Seat(6L, true),
                new Seat(4L, true),
                new Seat(5L, true),
                new Seat(7L, true),
                new Seat(8L, true),
                new Seat(6L, true),
                new Seat(4L, true),
                new Seat(8L, true),
                new Seat(6L, true),
                new Seat(4L, true),
                new Seat(5L, true),
                new Seat(7L, true),
                new Seat(8L, true),
                new Seat(6L, true),
                new Seat(4L, true),
                new Seat(8L, true),
                new Seat(6L, true),
                new Seat(4L, true),
                new Seat(5L, true),
                new Seat(7L, true),
                new Seat(8L, true),
                new Seat(6L, true),
                new Seat(4L, true)
        );
    }
}
