package com.ttbmp.cinehub.data.local.mock;

import com.ttbmp.cinehub.core.entity.Employee;
import com.ttbmp.cinehub.core.entity.Projectionist;
import com.ttbmp.cinehub.core.entity.Usher;
import com.ttbmp.cinehub.core.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Fabio Buracchi
 */
public class MockEmployeeRepository implements EmployeeRepository {

    private static final List<Employee> employeeList = new ArrayList<>();

    static {
        employeeList.add(new Usher(
                "Fabio",
                "Buracchi",
                new MockCinemaRepository().getCinema(0)
        ));
        employeeList.add(new Projectionist(
                "Massimo",
                "Mazzetti",
                new MockCinemaRepository().getCinema(0)
        ));
        employeeList.add(new Usher(
                "Ivan",
                "Palmieri",
                new MockCinemaRepository().getCinema(1)
        ));
        employeeList.add(new Projectionist(
                "ciao",
                "bella",
                new MockCinemaRepository().getCinema(1)
        ));
    }

    @Override
    public Employee getEmployee(int userId) {
        return employeeList.get(userId);
    }

}
