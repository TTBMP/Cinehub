package com.ttbmp.cinehub.app.repository.shift.projectionist;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;

/**
 * @author Fabio Buracchi
 */
public interface ProjectionistShiftRepository {

    ProjectionistShift getProjectionistShift(int shiftId) throws RepositoryException;

    void saveShift(ProjectionistShift shift) throws RepositoryException;

    void modifyShift(ProjectionistShift shift) throws RepositoryException;
}
