package com.ttbmp.cinehub.core.repository;

import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.entity.Hall;

import java.util.List;

/**
 * @author Massimo Mazzetti
 */
public interface HallRepository {

    List<Hall> getCinemaHallList(Cinema cinema);

}
