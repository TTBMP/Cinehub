package com.ttbmp.cinehub.app.repository.employee;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.cinema.CinemaRepository;
import com.ttbmp.cinehub.app.repository.employee.projectionist.ProjectionistProxy;
import com.ttbmp.cinehub.app.repository.employee.usher.UsherProxy;
import com.ttbmp.cinehub.app.repository.shift.MockShiftRepository;
import com.ttbmp.cinehub.app.repository.shift.ShiftRepository;
import com.ttbmp.cinehub.app.repository.user.UserRepository;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.shift.Shift;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Fabio Buracchi
 */
public class MockEmployeeRepository implements EmployeeRepository {

    private static final List<EmployeeData> EMPLOYEE_DATA_LIST = new ArrayList<>();

    static {
        var usherNumber = 2;
        var projectionistNumber = 12;
        IntStream.range(0, usherNumber).forEach(i -> EMPLOYEE_DATA_LIST.add(
                new EmployeeData(
                        Integer.toString(i * 2),
                        (i < usherNumber / 2) ? 1 : 2,
                        EmployeeData.Role.USHER)
        ));
        IntStream.range(0, projectionistNumber).forEach(i -> EMPLOYEE_DATA_LIST.add(
                new EmployeeData(
                        Integer.toString(i * 2 + 1),
                        (i < projectionistNumber / 2) ? 1 : 2,
                        EmployeeData.Role.PROJECTIONIST)
        ));
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
        private Role role;

        public EmployeeData(String userId, int cinemaId, Role role) {
            this.userId = userId;
            this.cinemaId = cinemaId;
            this.role = role;
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

        public Role getRole() {
            return role;
        }

        public void setRole(Role role) {
            this.role = role;
        }

        public enum Role {
            PROJECTIONIST,
            USHER
        }
    }

    class EmployeeFactory {
        Employee createEmployee(EmployeeData employeeData) {
            switch (employeeData.role) {
                case PROJECTIONIST:
                    return new ProjectionistProxy(
                            employeeData.userId,
                            serviceLocator.getService(UserRepository.class),
                            serviceLocator.getService(CinemaRepository.class),
                            serviceLocator.getService(ShiftRepository.class)
                    );
                case USHER:
                    return new UsherProxy(
                            employeeData.userId,
                            serviceLocator.getService(UserRepository.class),
                            serviceLocator.getService(CinemaRepository.class),
                            serviceLocator.getService(ShiftRepository.class)
                    );
                default:
                    throw new IllegalStateException("Unexpected value: " + employeeData.role);
            }
        }
    }

}
