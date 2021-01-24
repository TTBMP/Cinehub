package com.ttbmp.cinehub.core.repository;
/**
 * @author Palmieri Ivan
 */

import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.entity.Movie;

import java.time.LocalDate;
import java.util.List;

public interface TimeRepository {

    List<String> getTimeOfProjection(Cinema cinema, Movie movie, LocalDate date);
}
