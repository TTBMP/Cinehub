package com.ttbmp.cinehub.core.repository;


import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.entity.Movie;

import java.time.LocalDate;
import java.util.List;
/**
 * @author Palmieri Ivan
 */
public interface TimeRepository {

    List<String> getTimeOfProjection(Cinema cinema, Movie movie, LocalDate date);
}
