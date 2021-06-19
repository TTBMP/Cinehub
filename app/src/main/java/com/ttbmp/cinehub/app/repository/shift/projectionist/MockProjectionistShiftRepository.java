package com.ttbmp.cinehub.app.repository.shift.projectionist;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.employee.MockEmployeeRepository;
import com.ttbmp.cinehub.app.repository.hall.MockHallRepository;
import com.ttbmp.cinehub.app.repository.shift.MockShiftRepository;
import com.ttbmp.cinehub.app.repository.user.MockUserRepository;
import com.ttbmp.cinehub.app.utilities.repository.MockRepository;
import com.ttbmp.cinehub.domain.security.Role;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class MockProjectionistShiftRepository extends MockRepository implements ProjectionistShiftRepository {

    public static final String SHIFT_ID = "shiftId";
    public static final String HALL_ID = "hallId";

    private static final List<Map<String, String>> mockDataList = getMockDataList(MockProjectionistShiftRepository.class);

    static {
        var projectionistIdList = MockEmployeeRepository.getMockDataList().stream()
                .map(m -> m.get(MockEmployeeRepository.USER_ID))
                .filter(userId -> MockUserRepository.getMockDataList().stream()
                        .filter(m -> m.get(MockUserRepository.ID).equals(userId))
                        .findAny()
                        .map(m -> MockUserRepository.getRoleList(userId))
                        .orElse(new ArrayList<>())
                        .contains(Role.PROJECTIONIST_ROLE)
                )
                .collect(Collectors.toList());
        var projectionistShiftIdList = MockShiftRepository.getMockDataList().stream()
                .filter(m -> projectionistIdList.contains(m.get(MockShiftRepository.EMPLOYEE_ID)))
                .map(m -> m.get(MockShiftRepository.ID))
                .collect(Collectors.toList());
        var hallId = 1;
        for (var projectionistShiftId : projectionistShiftIdList) {
            mockDataList.add(new HashMap<>(Map.of(SHIFT_ID, projectionistShiftId, HALL_ID, Integer.toString(hallId))));
            hallId = (hallId % MockHallRepository.getMockDataList().size()) + 1;
        }
    }

    public MockProjectionistShiftRepository(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    public static List<Map<String, String>> getMockDataList() {
        return mockDataList;
    }

    @Override
    public ProjectionistShift getProjectionistShift(int shiftId) {
        return MockShiftRepository.getMockDataList().stream()
                .filter(m -> m.get(MockShiftRepository.ID).equals(Integer.toString(shiftId)))
                .findAny()
                .map(m -> new ProjectionistShiftProxy(
                        getServiceLocator(),
                        Integer.parseInt(m.get(MockShiftRepository.ID)),
                        m.get(MockShiftRepository.DATE),
                        m.get(MockShiftRepository.START),
                        m.get(MockShiftRepository.END)
                ))
                .orElse(null);
    }

    @Override
    public void saveShift(ProjectionistShift shift) {
        mockDataList.add(new HashMap<>(Map.of(
                SHIFT_ID, Integer.toString(shift.getId()),
                HALL_ID, Integer.toString(shift.getHall().getId())
        )));
    }

    @Override
    public void modifyShift(ProjectionistShift shift) throws RepositoryException {
        var dataMap = mockDataList.stream()
                .filter(m -> m.get(SHIFT_ID).equals(Integer.toString(shift.getId())))
                .findAny()
                .orElseThrow(() -> new RepositoryException("Shift does not exists."));
        dataMap.put(HALL_ID, Integer.toString(shift.getHall().getId()));
    }

}
