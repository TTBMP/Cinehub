package com.ttbmp.cinehub.app.repository.hall;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;

import java.util.List;

/**
 * @author Massimo Mazzetti
 */
public interface HallRepository {

    Hall getHall(int hallId) throws RepositoryException;

    Hall getHall(Projection projection) throws RepositoryException;

    Hall getHall(ProjectionistShift projectionistShift) throws RepositoryException;

    List<Hall> getHallList(Cinema cinema) throws RepositoryException;

}
