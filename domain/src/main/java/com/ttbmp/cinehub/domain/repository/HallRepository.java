package com.ttbmp.cinehub.domain.repository;

import com.ttbmp.cinehub.domain.entity.Cinema;
import com.ttbmp.cinehub.domain.entity.Hall;

import java.util.List;

/**
 * @author Massimo Mazzetti
 */
public interface HallRepository {

    List<Hall> getCinemaHallList(Cinema cinema);

}
