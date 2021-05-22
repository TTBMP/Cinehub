package com.ttbmp.cinehub.app.repository.employee.usher;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.cinema.CinemaRepository;
import com.ttbmp.cinehub.app.repository.shift.MockShiftRepository;
import com.ttbmp.cinehub.app.repository.shift.ShiftRepository;
import com.ttbmp.cinehub.app.repository.user.UserRepository;
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
                .map(shiftData -> new UsherProxy(
                        shiftData.getEmployeeId(),
                        serviceLocator.getService(UserRepository.class),
                        serviceLocator.getService(CinemaRepository.class),
                        serviceLocator.getService(ShiftRepository.class))
                )
                .findAny()
                .orElse(null);
    }

}
