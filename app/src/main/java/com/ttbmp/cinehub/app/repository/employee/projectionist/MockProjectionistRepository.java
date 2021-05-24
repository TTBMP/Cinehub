package com.ttbmp.cinehub.app.repository.employee.projectionist;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.projection.MockProjectionRepository;
import com.ttbmp.cinehub.app.repository.shift.MockShiftRepository;
import com.ttbmp.cinehub.app.repository.shift.projectionist.MockProjectionistShiftRepository;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.employee.Projectionist;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;

import java.time.LocalTime;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class MockProjectionistRepository implements ProjectionistRepository {

    private final ServiceLocator serviceLocator;

    public MockProjectionistRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public Projectionist getProjectionist(Projection projection) {
        return MockProjectionRepository.getProjectionDataList().stream()
                .filter(d -> d.getId() == projection.getId())
                .findAny()
                .flatMap(projectionData -> {
                    var projectionistShiftIdList = MockProjectionistShiftRepository.getProjectionistShiftDataList().stream()
                            .filter(projectionistShiftData -> projectionData.getHallId() == projectionistShiftData.getHallId())
                            .map(MockProjectionistShiftRepository.ProjectionistShiftData::getShiftId)
                            .collect(Collectors.toList());
                    var shiftList = MockShiftRepository.getShiftDataList().stream()
                            .filter(shiftData -> projectionData.getDate().equals(shiftData.getDate()))
                            .filter(shiftData -> LocalTime.parse(projectionData.getStartTime()).isAfter(LocalTime.parse(shiftData.getStart())))
                            .filter(shiftData -> LocalTime.parse(projectionData.getStartTime()).isBefore(LocalTime.parse(shiftData.getEnd())))
                            .collect(Collectors.toList());
                    shiftList.removeIf(shiftData -> !projectionistShiftIdList.contains(shiftData.getId()));
                    return shiftList.stream()
                            .findAny()
                            .map(MockShiftRepository.ShiftData::getEmployeeId)
                            .map(projectionistId -> new ProjectionistProxy(serviceLocator, projectionistId));
                })
                .orElse(null);
    }

    @Override
    public Projectionist getProjectionist(ProjectionistShift projectionistShift) {
        return MockShiftRepository.getShiftDataList().stream()
                .filter(d -> d.getId() == projectionistShift.getId())
                .map(shiftData -> new ProjectionistProxy(serviceLocator, shiftData.getEmployeeId()))
                .findAny()
                .orElse(null);
    }

}
