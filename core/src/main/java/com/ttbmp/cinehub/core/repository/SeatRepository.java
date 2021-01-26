package com.ttbmp.cinehub.core.repository;


import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.entity.Movie;
import com.ttbmp.cinehub.core.entity.Seat;

import java.time.LocalDate;
import java.util.List;
/**
 * @author Palmieri Ivan
 */
public interface SeatRepository {
    List<Seat> getListSeat(Cinema cinema, Movie movie, LocalDate date, String time);
}
