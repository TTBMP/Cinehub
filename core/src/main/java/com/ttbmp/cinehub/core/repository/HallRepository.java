package com.ttbmp.cinehub.core.repository;

import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.entity.Hall;
import com.ttbmp.cinehub.core.entity.Projection;

import java.util.List;
/**
 * @author Palmieri Ivan
 */
public interface HallRepository {

    List<Hall> retriveHallByCinema(Cinema cinema);

    Hall retriveHallByProjection(Projection projection);

    List<Hall> retriveAllHall();
}
