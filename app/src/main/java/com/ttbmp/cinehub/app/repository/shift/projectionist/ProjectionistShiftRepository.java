package com.ttbmp.cinehub.app.repository.shift.projectionist;

import com.ttbmp.cinehub.domain.shift.ProjectionistShift;

/**
 * @author Fabio Buracchi
 */
public interface ProjectionistShiftRepository {

    ProjectionistShift getProjectionistShift(int shiftId);

}
