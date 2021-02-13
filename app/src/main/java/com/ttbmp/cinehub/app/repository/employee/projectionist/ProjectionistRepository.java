package com.ttbmp.cinehub.app.repository.employee.projectionist;

import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.employee.Projectionist;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;

/**
 * @author Fabio Buracchi
 */
public interface ProjectionistRepository {

    Projectionist getProjectionist(Projection projection);

    Projectionist getProjectionist(ProjectionistShift projectionistShift);

}
