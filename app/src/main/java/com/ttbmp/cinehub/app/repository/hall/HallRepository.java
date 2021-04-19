package com.ttbmp.cinehub.app.repository.hall;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import java.util.List;

/**
 * @author Massimo Mazzetti
 */
public interface HallRepository {

    List<Hall> getHallList(Cinema cinema) throws RepositoryException;

    Hall getHall(Projection projection) throws  RepositoryException;

    Hall getHall(ProjectionistShift projectionistShift) throws  RepositoryException;

    Hall getHall(int hallId) throws  RepositoryException;

}
