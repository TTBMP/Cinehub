package com.ttbmp.cinehub.app.repository.shift.usher;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.LazyLoadingException;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.employee.usher.UsherRepository;
import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.shift.UsherShift;
import lombok.EqualsAndHashCode;

/**
 * @author Fabio Buracchi
 */
@EqualsAndHashCode(callSuper = true)
public class UsherShiftProxy extends UsherShift {

    private final UsherRepository usherRepository;

    private boolean isEmployeeLoaded = false;

    public UsherShiftProxy(ServiceLocator serviceLocator, int id, String date, String start, String end) {
        super(id, null, date, start, end);
        this.usherRepository = serviceLocator.getService(UsherRepository.class);
    }

    @Override
    public Employee getEmployee() {
        if (!isEmployeeLoaded) {
            try {
                setEmployee(usherRepository.getUsher(this));
            } catch (RepositoryException e) {
                throw new LazyLoadingException(e.getMessage());

            }
        }
        return super.getEmployee();
    }

    @Override
    public void setEmployee(Employee employee) {
        isEmployeeLoaded = true;
        super.setEmployee(employee);
    }

}
