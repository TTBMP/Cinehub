package com.ttbmp.cinehub.app.repository.projection;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.Movie;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;

import java.util.List;

/**
 * @author Ivan Palmieri
 */
public interface ProjectionRepository {

    Projection getProjection(int id) throws RepositoryException;

    Projection getProjection(String date, String time, Hall hall) throws RepositoryException;

    List<Projection> getProjectionList(ProjectionistShift shift) throws RepositoryException;

    List<Projection> getProjectionList(Cinema cinema, Movie movie, String date) throws RepositoryException;


}
