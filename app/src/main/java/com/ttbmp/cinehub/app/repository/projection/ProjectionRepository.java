package com.ttbmp.cinehub.app.repository.projection;

import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.Movie;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;

import java.util.List;

/**
 * @author Ivan Palmieri
 */
public interface ProjectionRepository {

    Projection getProjection(String date, String time, Integer hallId);

    List<Projection> getProjectionList(ProjectionistShift shift);

    List<Projection> getProjectionList(Cinema cinema, Movie movie, String date);

    List<Projection> getProjectionList(Cinema cinema, Movie movie, String date, Integer hallId);

}
