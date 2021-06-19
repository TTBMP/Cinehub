package com.ttbmp.cinehub.app.repository.employee.projectionist;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.projection.MockProjectionRepository;
import com.ttbmp.cinehub.app.repository.shift.MockShiftRepository;
import com.ttbmp.cinehub.app.repository.shift.projectionist.MockProjectionistShiftRepository;
import com.ttbmp.cinehub.app.utilities.repository.MockRepository;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.employee.Projectionist;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;

import java.time.LocalTime;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class MockProjectionistRepository extends MockRepository implements ProjectionistRepository {

    public MockProjectionistRepository(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public Projectionist getProjectionist(Projection projection) {
        return MockProjectionRepository.getMockDataList().stream()
                .filter(m -> m.get(MockProjectionRepository.ID).equals(Integer.toString(projection.getId())))
                .findAny()
                .flatMap(projectionDataMap -> {
                    var projectionistShiftIdList = MockProjectionistShiftRepository.getMockDataList().stream()
                            .filter(m -> projectionDataMap.get(MockProjectionRepository.HALL_ID).equals(m.get(MockProjectionistShiftRepository.HALL_ID)))
                            .map(m -> m.get(MockProjectionistShiftRepository.SHIFT_ID))
                            .collect(Collectors.toList());
                    var shiftList = MockShiftRepository.getMockDataList().stream()
                            .filter(m -> projectionDataMap.get(MockProjectionRepository.DATE).equals(m.get(MockShiftRepository.DATE)))
                            .filter(m -> LocalTime.parse(projectionDataMap.get(MockProjectionRepository.START_TIME)).isAfter(LocalTime.parse(m.get(MockShiftRepository.START))))
                            .filter(m -> LocalTime.parse(projectionDataMap.get(MockProjectionRepository.START_TIME)).isBefore(LocalTime.parse(m.get(MockShiftRepository.END))))
                            .collect(Collectors.toList());
                    shiftList.removeIf(m -> !projectionistShiftIdList.contains(m.get(MockShiftRepository.ID)));
                    return shiftList.stream()
                            .findAny()
                            .map(m -> m.get(MockShiftRepository.EMPLOYEE_ID))
                            .map(projectionistId -> new ProjectionistProxy(getServiceLocator(), projectionistId));
                })
                .orElse(null);
    }

    @Override
    public Projectionist getProjectionist(ProjectionistShift projectionistShift) {
        return MockShiftRepository.getMockDataList().stream()
                .filter(m -> m.get(MockShiftRepository.ID).equals(Integer.toString(projectionistShift.getId())))
                .map(m -> new ProjectionistProxy(getServiceLocator(), m.get(MockShiftRepository.EMPLOYEE_ID)))
                .findAny()
                .orElse(null);
    }

}
