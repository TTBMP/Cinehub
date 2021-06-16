package com.ttbmp.cinehub.app.repository.employee;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.employee.projectionist.ProjectionistProxy;
import com.ttbmp.cinehub.app.repository.employee.usher.UsherProxy;
import com.ttbmp.cinehub.app.repository.shift.MockShiftRepository;
import com.ttbmp.cinehub.app.repository.user.MockRoleRepository;
import com.ttbmp.cinehub.app.repository.user.MockUserRepository;
import com.ttbmp.cinehub.app.repository.user.MockUserRoleRepository;
import com.ttbmp.cinehub.app.utilities.repository.MockRepository;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.security.Role;
import com.ttbmp.cinehub.domain.shift.Shift;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class MockEmployeeRepository extends MockRepository implements EmployeeRepository {

    public static final String USER_ID = "userId";
    public static final String CINEMA_ID = "cinemaId";

    private static final List<Map<String, String>> mockDataList = getMockDataList(MockEmployeeRepository.class);

    static {
        var usherRoleId = MockRoleRepository.getMockDataList().stream()
                .filter(m -> m.get(MockRoleRepository.NAME).equals("usher"))
                .map(m -> m.get(MockRoleRepository.ID))
                .findAny()
                .orElse(null);
        var projectionistRoleId = MockRoleRepository.getMockDataList().stream()
                .filter(m -> m.get(MockRoleRepository.NAME).equals("projectionist"))
                .map(m -> m.get(MockRoleRepository.ID))
                .findAny()
                .orElse(null);
        var usherIdList = MockUserRoleRepository.getMockDataList().stream()
                .filter(m -> m.get(MockUserRoleRepository.ID_ROLE).equals(usherRoleId))
                .map(m -> m.get(MockUserRoleRepository.ID_USER))
                .collect(Collectors.toList());
        var projectionistIdList = MockUserRoleRepository.getMockDataList().stream()
                .filter(m -> m.get(MockUserRoleRepository.ID_ROLE).equals(projectionistRoleId))
                .map(m -> m.get(MockUserRoleRepository.ID_USER))
                .collect(Collectors.toList());
        MockUserRepository.getMockDataList().stream()
                .map(m -> m.get(MockUserRepository.ID))
                .filter(usherIdList::contains)
                .limit(usherIdList.size() / 2)
                .forEach(userId -> mockDataList.add(Map.of(USER_ID, userId, CINEMA_ID, "1")));
        MockUserRepository.getMockDataList().stream()
                .map(m -> m.get(MockUserRepository.ID))
                .filter(usherIdList::contains)
                .skip(usherIdList.size() / 2)
                .forEach(userId -> mockDataList.add(Map.of(USER_ID, userId, CINEMA_ID, "2")));
        MockUserRepository.getMockDataList().stream()
                .map(m -> m.get(MockUserRepository.ID))
                .filter(projectionistIdList::contains)
                .limit(projectionistIdList.size() / 2)
                .forEach(userId -> mockDataList.add(Map.of(USER_ID, userId, CINEMA_ID, "1")));
        MockUserRepository.getMockDataList().stream()
                .map(m -> m.get(MockUserRepository.ID))
                .filter(projectionistIdList::contains)
                .skip(projectionistIdList.size() / 2)
                .forEach(userId -> mockDataList.add(Map.of(USER_ID, userId, CINEMA_ID, "2")));
    }

    public MockEmployeeRepository(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    public static List<Map<String, String>> getMockDataList() {
        return mockDataList;
    }

    @Override
    public Employee getEmployee(String userId) {
        return mockDataList.stream()
                .filter(m -> m.get(USER_ID).equals(userId))
                .findAny()
                .map(m -> new EmployeeFactory().createEmployee(m))
                .orElse(null);
    }

    @Override
    public Employee getEmployee(Shift shift) {
        return MockShiftRepository.getMockDataList().stream()
                .filter(m -> m.get(MockShiftRepository.ID).equals(Integer.toString(shift.getId())))
                .findAny()
                .map(m -> m.get(MockShiftRepository.EMPLOYEE_ID))
                .flatMap(shiftEmployeeId -> mockDataList.stream()
                        .filter(m -> m.get(USER_ID).equals(shiftEmployeeId))
                        .findAny()
                        .map(m -> new EmployeeFactory().createEmployee(m))
                )
                .orElse(null);
    }

    @Override
    public List<Employee> getEmployeeList(Cinema cinema) {
        return mockDataList.stream()
                .filter(m -> m.get(CINEMA_ID).equals(Integer.toString(cinema.getId())))
                .map(m -> new EmployeeFactory().createEmployee(m))
                .collect(Collectors.toList());
    }

    class EmployeeFactory {
        Employee createEmployee(Map<String, String> employeeDataMap) {
            var roleList = MockUserRepository.getMockDataList().stream()
                    .filter(m -> m.get(MockUserRepository.ID).equals(employeeDataMap.get(USER_ID)))
                    .findAny()
                    .map(m -> MockUserRepository.getRoleList(m.get(MockUserRepository.ID)))
                    .orElse(new ArrayList<>());
            if (roleList.contains(Role.PROJECTIONIST_ROLE)) {
                return new ProjectionistProxy(getServiceLocator(), employeeDataMap.get(USER_ID));
            } else if (roleList.contains(Role.USHER_ROLE)) {
                return new UsherProxy(getServiceLocator(), employeeDataMap.get(USER_ID));
            } else {
                throw new IllegalStateException("Unexpected user: " + employeeDataMap.get(USER_ID));
            }
        }
    }

}
