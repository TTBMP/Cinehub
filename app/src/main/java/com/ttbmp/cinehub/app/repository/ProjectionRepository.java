package com.ttbmp.cinehub.app.repository;

import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.dto.MovieDto;
import com.ttbmp.cinehub.domain.Movie;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;
import com.ttbmp.cinehub.domain.shift.Shift;

import java.util.List;

/**
 * @author Palmieri Ivan
 */
public interface ProjectionRepository {

    List<Projection> getProjectionList(CinemaDto mapToDto, MovieDto mapToDto1, String date);

    List<Projection> getProjectionList(String localDate);

    List<Projection> getProjectionList(Movie movie, String date);

    List<Projection> getProjectionList(ProjectionistShift shift);

}
