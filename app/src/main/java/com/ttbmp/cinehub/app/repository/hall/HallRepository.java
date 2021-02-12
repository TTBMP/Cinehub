package com.ttbmp.cinehub.app.repository.hall;

import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.Hall;

import java.util.List;

/**
 * @author Massimo Mazzetti
 */
public interface HallRepository {

    List<Hall> getCinemaHallList(int cinemaId);

}
