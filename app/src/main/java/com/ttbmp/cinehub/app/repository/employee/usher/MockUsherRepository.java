package com.ttbmp.cinehub.app.repository.employee.usher;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.shift.MockShiftRepository;
import com.ttbmp.cinehub.app.utilities.repository.MockRepository;
import com.ttbmp.cinehub.domain.employee.Usher;
import com.ttbmp.cinehub.domain.shift.UsherShift;

/**
 * @author Fabio Buracchi
 */
public class MockUsherRepository extends MockRepository implements UsherRepository {

    public MockUsherRepository(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public Usher getUsher(UsherShift usherShift) {
        return MockShiftRepository.getMockDataList().stream()
                .filter(m -> m.get(MockShiftRepository.ID).equals(Integer.toString(usherShift.getId())))
                .map(m -> new UsherProxy(getServiceLocator(), m.get(MockShiftRepository.EMPLOYEE_ID)))
                .findAny()
                .orElse(null);
    }

}
