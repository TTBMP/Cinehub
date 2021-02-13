package com.ttbmp.cinehub.app.repository.employee;

import com.ttbmp.cinehub.app.repository.cinema.MockCinemaRepository;
import com.ttbmp.cinehub.app.repository.user.MockUserRepository;
import com.ttbmp.cinehub.domain.Employee;
import com.ttbmp.cinehub.domain.Projectionist;
import com.ttbmp.cinehub.domain.Usher;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Fabio Buracchi
 */
public class MockEmployeeRepository implements EmployeeRepository {

    private static final List<Employee> employeeList = new ArrayList<>();

    static {
        MockUserRepository userRepository = new MockUserRepository();
        employeeList.add(new Usher(
                userRepository.getUser("0"),
                new MockCinemaRepository().getCinema(0)
        ));
        employeeList.add(new Projectionist(
                userRepository.getUser("1"),
                new MockCinemaRepository().getCinema(0)
        ));
        employeeList.add(new Usher(
                userRepository.getUser("2"),
                new MockCinemaRepository().getCinema(1)
        ));
        employeeList.add(new Projectionist(
                userRepository.getUser("3"),
                new MockCinemaRepository().getCinema(1)
        ));
    }

    @Override
    public Employee getEmployee(String userId) {
        return employeeList.get(Integer.parseInt(userId));
    }

}
