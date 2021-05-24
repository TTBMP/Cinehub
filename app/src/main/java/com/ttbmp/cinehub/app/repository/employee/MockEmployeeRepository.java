package com.ttbmp.cinehub.app.repository.employee;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.employee.projectionist.ProjectionistProxy;
import com.ttbmp.cinehub.app.repository.employee.usher.UsherProxy;
import com.ttbmp.cinehub.app.repository.shift.MockShiftRepository;
import com.ttbmp.cinehub.app.repository.user.MockUserRepository;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.security.Role;
import com.ttbmp.cinehub.domain.shift.Shift;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class MockEmployeeRepository implements EmployeeRepository {

    private static final List<EmployeeData> EMPLOYEE_DATA_LIST = new ArrayList<>();

    static {
        var usherIdList = MockUserRepository.getRoleDataList().stream()
                .filter(roleData -> roleData.getName().equals("usher"))
                .findAny()
                .map(roleData -> MockUserRepository.getUserRoleDataList().stream()
                        .filter(userRoleData -> userRoleData.getIdRole() == roleData.getId())
                        .map(MockUserRepository.UserRoleData::getIdUser)
                        .collect(Collectors.toList()))
                .orElse(new ArrayList<>());
        var projectionistIdList = MockUserRepository.getRoleDataList().stream()
                .filter(roleData -> roleData.getName().equals("projectionist"))
                .findAny()
                .map(roleData -> MockUserRepository.getUserRoleDataList().stream()
                        .filter(userRoleData -> userRoleData.getIdRole() == roleData.getId())
                        .map(MockUserRepository.UserRoleData::getIdUser)
                        .collect(Collectors.toList()))
                .orElse(new ArrayList<>());
        MockUserRepository.getUserDataList().stream()
                .map(MockUserRepository.UserData::getId)
                .filter(usherIdList::contains)
                .limit(usherIdList.size() / 2)
                .forEach(userId -> EMPLOYEE_DATA_LIST.add(new EmployeeData(userId, 1)));
        MockUserRepository.getUserDataList().stream()
                .map(MockUserRepository.UserData::getId)
                .filter(usherIdList::contains)
                .skip(usherIdList.size() / 2)
                .forEach(userId -> EMPLOYEE_DATA_LIST.add(new EmployeeData(userId, 2)));
        MockUserRepository.getUserDataList().stream()
                .map(MockUserRepository.UserData::getId)
                .filter(projectionistIdList::contains)
                .limit(usherIdList.size() / 2)
                .forEach(userId -> EMPLOYEE_DATA_LIST.add(new EmployeeData(userId, 1)));
        MockUserRepository.getUserDataList().stream()
                .map(MockUserRepository.UserData::getId)
                .filter(projectionistIdList::contains)
                .skip(usherIdList.size() / 2)
                .forEach(userId -> EMPLOYEE_DATA_LIST.add(new EmployeeData(userId, 2)));
    }

    private final ServiceLocator serviceLocator;

    public MockEmployeeRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public static List<EmployeeData> getEmployeeDataList() {
        return EMPLOYEE_DATA_LIST;
    }

    @Override
    public Employee getEmployee(String userId) {
        return EMPLOYEE_DATA_LIST.stream()
                .filter(d -> d.getUserId().equals(userId))
                .findAny()
                .map(d -> new EmployeeFactory().createEmployee(d))
                .orElse(null);
    }

    @Override
    public Employee getEmployee(Shift shift) {
        return MockShiftRepository.getShiftDataList().stream()
                .filter(d -> d.getId() == shift.getId())
                .findAny()
                .map(MockShiftRepository.ShiftData::getEmployeeId)
                .flatMap(shiftEmployeeId -> EMPLOYEE_DATA_LIST.stream()
                        .filter(d -> d.userId.equals(shiftEmployeeId))
                        .findAny()
                        .map(d -> new EmployeeFactory().createEmployee(d))
                )
                .orElse(null);
    }

    @Override
    public List<Employee> getEmployeeList(Cinema cinema) {
        return EMPLOYEE_DATA_LIST.stream()
                .filter(d -> d.getCinemaId() == cinema.getId())
                .map(d -> new EmployeeFactory().createEmployee(d))
                .collect(Collectors.toList());
    }

    public static class EmployeeData {

        private String userId;
        private int cinemaId;

        public EmployeeData(String userId, int cinemaId) {
            this.userId = userId;
            this.cinemaId = cinemaId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public int getCinemaId() {
            return cinemaId;
        }

        public void setCinemaId(int cinemaId) {
            this.cinemaId = cinemaId;
        }

    }

    class EmployeeFactory {
        Employee createEmployee(EmployeeData employeeData) {
            var roleList = MockUserRepository.getUserDataList().stream()
                    .filter(d -> d.getId().equals(employeeData.userId))
                    .findAny()
                    .map(MockUserRepository.UserData::getRoleList)
                    .orElse(new ArrayList<>());
            if (roleList.contains(Role.PROJECTIONIST_ROLE)) {
                return new ProjectionistProxy(serviceLocator, employeeData.userId);
            } else if (roleList.contains(Role.USHER_ROLE)) {
                return new UsherProxy(serviceLocator, employeeData.userId);
            } else {
                throw new IllegalStateException("Unexpected user: " + employeeData.userId);
            }
        }
    }

}
