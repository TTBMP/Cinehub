package com.ttbmp.cinehub.app.repository.shift.usher;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.utilities.repository.MockRepository;

/**
 * @author Fabio Buracchi
 */
public class MockUsherShiftRepository extends MockRepository implements UsherShiftRepository {

    public MockUsherShiftRepository(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

}
