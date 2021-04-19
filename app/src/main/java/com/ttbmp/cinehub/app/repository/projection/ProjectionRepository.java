package com.ttbmp.cinehub.app.repository.projection;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.Movie;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import java.util.List;

/**
 * @author Palmieri Ivan
 */
public interface ProjectionRepository {

    List<Projection> getProjectionList(Cinema cinema, Movie movie, String date) throws  RepositoryException;

    List<Projection> getProjectionList(String localDate) throws  RepositoryException;

    List<Projection> getProjectionList(Movie movie, String date) throws  RepositoryException;

    List<Projection> getProjectionList(ProjectionistShift shift) throws  RepositoryException;

}
