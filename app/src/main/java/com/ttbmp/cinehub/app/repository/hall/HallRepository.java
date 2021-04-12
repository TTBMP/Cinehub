package com.ttbmp.cinehub.app.repository.hall;

import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;

import java.util.List;

/**
 * @author Massimo Mazzetti
 */
public interface HallRepository {

    List<Hall> getHallList(int cinema);

    Hall getHall(Projection projection);

    Hall getHall(ProjectionistShift projectionistShift);

    Hall getHall(int hallId);

}
