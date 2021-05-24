package com.ttbmp.cinehub.app.repository.shift.projectionist;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.employee.MockEmployeeRepository;
import com.ttbmp.cinehub.app.repository.hall.MockHallRepository;
import com.ttbmp.cinehub.app.repository.shift.MockShiftRepository;
import com.ttbmp.cinehub.app.repository.user.MockUserRepository;
import com.ttbmp.cinehub.domain.security.Role;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class MockProjectionistShiftRepository implements ProjectionistShiftRepository {

    private static final List<ProjectionistShiftData> PROJECTIONIST_SHIFT_DATA_LIST = new ArrayList<>();

    static {
        var projectionistIdList = MockEmployeeRepository.getEmployeeDataList().stream()
                .map(MockEmployeeRepository.EmployeeData::getUserId)
                .filter(userId -> MockUserRepository.getUserDataList().stream()
                        .filter(d -> d.getId().equals(userId))
                        .findAny()
                        .map(MockUserRepository.UserData::getRoleList)
                        .orElse(new ArrayList<>())
                        .contains(Role.PROJECTIONIST_ROLE)
                )
                .collect(Collectors.toList());
        var projectionistShiftIdList = MockShiftRepository.getShiftDataList().stream()
                .filter(shiftData -> projectionistIdList.contains(shiftData.getEmployeeId()))
                .map(MockShiftRepository.ShiftData::getId)
                .collect(Collectors.toList());
        var hallId = 1;
        for (var projectionistShiftId : projectionistShiftIdList) {
            PROJECTIONIST_SHIFT_DATA_LIST.add(new ProjectionistShiftData(projectionistShiftId, hallId));
            hallId = (hallId % MockHallRepository.getHallDataList().size()) + 1;
        }
    }

    private final ServiceLocator serviceLocator;

    public MockProjectionistShiftRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public static List<ProjectionistShiftData> getProjectionistShiftDataList() {
        return PROJECTIONIST_SHIFT_DATA_LIST;
    }

    @Override
    public ProjectionistShift getProjectionistShift(int shiftId) {
        return MockShiftRepository.getShiftDataList().stream()
                .filter(d -> d.getId() == shiftId)
                .findAny()
                .map(shiftData -> new ProjectionistShiftProxy(
                        serviceLocator,
                        shiftData.getId(),
                        shiftData.getDate(),
                        shiftData.getStart(),
                        shiftData.getEnd())
                )
                .orElse(null);
    }

    @Override
    public void saveShift(ProjectionistShift shift) {
        PROJECTIONIST_SHIFT_DATA_LIST.add(new ProjectionistShiftData(shift.getId(), shift.getHall().getId()));
    }

    @Override
    public void modifyShift(ProjectionistShift shift) throws RepositoryException {
        var data = PROJECTIONIST_SHIFT_DATA_LIST.stream()
                .filter(d -> d.shiftId == shift.getId())
                .findAny()
                .orElseThrow(() -> new RepositoryException("Shift does not exists."));
        data.shiftId = shift.getId();
        data.hallId = shift.getHall().getId();
    }

    public static class ProjectionistShiftData {

        private int shiftId;
        private int hallId;

        public ProjectionistShiftData(int shiftId, int hallId) {
            this.shiftId = shiftId;
            this.hallId = hallId;
        }

        public int getShiftId() {
            return shiftId;
        }

        public void setShiftId(int shiftId) {
            this.shiftId = shiftId;
        }

        public int getHallId() {
            return hallId;
        }

        public void setHallId(int hallId) {
            this.hallId = hallId;
        }

    }

}
