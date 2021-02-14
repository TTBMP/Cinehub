package com.ttbmp.cinehub.app.repository.employee.usher;

import com.ttbmp.cinehub.domain.employee.Usher;
import com.ttbmp.cinehub.domain.shift.UsherShift;

/**
 * @author Fabio Buracchi
 */
public interface UsherRepository {

    Usher getUsher(UsherShift usherShift);

}
