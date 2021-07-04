package com.ttbmp.cinehub.app.repository.shift.projectionist;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.LazyLoadingException;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.employee.projectionist.ProjectionistRepository;
import com.ttbmp.cinehub.app.repository.hall.HallRepository;
import com.ttbmp.cinehub.app.repository.projection.ProjectionRepository;
import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;

import java.util.List;

/**
 * @author Fabio Buracchi
 */
public class ProjectionistShiftProxy extends ProjectionistShift {

    private final ProjectionistRepository projectionistRepository;
    private final HallRepository hallRepository;
    private final ProjectionRepository projectionRepository;

    private boolean isProjectionistShiftEmployeeLoaded = false;
    private boolean isProjectionistShiftHallLoaded = false;
    private boolean isProjectionistShiftProjectionListLoaded = false;

    public ProjectionistShiftProxy(ServiceLocator serviceLocator, int id, String date, String start, String end) {
        super(id, null, date, start, end, null, null);
        this.projectionistRepository = serviceLocator.getService(ProjectionistRepository.class);
        this.hallRepository = serviceLocator.getService(HallRepository.class);
        this.projectionRepository = serviceLocator.getService(ProjectionRepository.class);
    }

    @Override
    public Employee getEmployee() {
        if (!isProjectionistShiftEmployeeLoaded) {
            try {
                setEmployee(projectionistRepository.getProjectionist(this));
            } catch (RepositoryException e) {
                throw new LazyLoadingException(e.getMessage());

            }
        }
        return super.getEmployee();
    }

    @Override
    public void setEmployee(Employee employee) {
        isProjectionistShiftEmployeeLoaded = true;
        super.setEmployee(employee);
    }

    @Override
    public Hall getHall() {
        try {
            if (!isProjectionistShiftHallLoaded) {
                setHall(hallRepository.getHall(this));
            }
            return super.getHall();
        } catch (RepositoryException e) {
            throw new LazyLoadingException(e.getMessage());
        }
    }

    @Override
    public void setHall(Hall hall) {
        isProjectionistShiftHallLoaded = true;
        super.setHall(hall);
    }

    @Override
    public List<Projection> getProjectionList() {
        try {
            if (!isProjectionistShiftProjectionListLoaded) {
                setProjectionList(projectionRepository.getProjectionList(this));
            }
            return super.getProjectionList();
        } catch (RepositoryException e) {
            throw new LazyLoadingException(e.getMessage());
        }
    }

    @Override
    public void setProjectionList(List<Projection> projectionList) {
        isProjectionistShiftProjectionListLoaded = true;
        super.setProjectionList(projectionList);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
