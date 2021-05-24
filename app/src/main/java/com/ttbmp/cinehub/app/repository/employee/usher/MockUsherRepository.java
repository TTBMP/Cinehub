package com.ttbmp.cinehub.app.repository.employee.usher;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.shift.MockShiftRepository;
import com.ttbmp.cinehub.domain.employee.Usher;
import com.ttbmp.cinehub.domain.shift.UsherShift;

/**
 * @author Fabio Buracchi
 */
public class MockUsherRepository implements UsherRepository {

    private final ServiceLocator serviceLocator;

    public MockUsherRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public Usher getUsher(UsherShift usherShift) {
        return MockShiftRepository.getShiftDataList().stream()
                .filter(d -> d.getId() == usherShift.getId())
                .map(shiftData -> new UsherProxy(serviceLocator, shiftData.getEmployeeId()))
                .findAny()
                .orElse(null);
    }

}
