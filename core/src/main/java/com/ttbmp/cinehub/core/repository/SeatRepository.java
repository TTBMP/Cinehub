package com.ttbmp.cinehub.core.repository;
/**
 * @author Palmieri Ivan
 */

import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.entity.Movie;
import com.ttbmp.cinehub.core.entity.Seat;

import java.time.LocalDate;
import java.util.List;

public interface SeatRepository {
    List<Seat> getListSeat(Cinema cinema, Movie movie, LocalDate date, String time);
}
