package com.ttbmp.cinehub.core.repository;

import com.ttbmp.cinehub.core.entity.Cinema;

import java.util.List;

/**
 * @author Massimo Mazzetti
 */

public interface CinemaRepository {
    List<Cinema> getAllCinema();
}
