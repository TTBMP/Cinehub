package com.ttbmp.cinehub.app.repository.shift.projectionist;

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

    private boolean isEmployeeLoaded = false;
    private boolean isHallLoaded = false;
    private boolean isProjectionListLoaded = false;

    public ProjectionistShiftProxy(
            int id,
            String date,
            String start,
            String end,
            ProjectionistRepository projectionistRepository,
            HallRepository hallRepository,
            ProjectionRepository projectionRepository) {
        super(id, null, date, start, end, null, null);
        this.projectionistRepository = projectionistRepository;
        this.hallRepository = hallRepository;
        this.projectionRepository = projectionRepository;
    }

    @Override
    public Employee getEmployee() {
        if (!isEmployeeLoaded) {
            setEmployee(projectionistRepository.getProjectionist(this));
        }
        return super.getEmployee();
    }

    @Override
    public void setEmployee(Employee employee) {
        isEmployeeLoaded = true;
        super.setEmployee(employee);
    }

    @Override
    public Hall getHall() {
        try {
            if (!isHallLoaded) {
                setHall(hallRepository.getHall(this));
            }
            return super.getHall();
        } catch (RepositoryException e) {
            throw new LazyLoadingException(e.getMessage());
        }
    }

    @Override
    public void setHall(Hall hall) {
        isHallLoaded = true;
        super.setHall(hall);
    }

    @Override
    public List<Projection> getProjectionList() {
        try {
            if (!isProjectionListLoaded) {
                setProjectionList(projectionRepository.getProjectionList(this));
            }
            return super.getProjectionList();
        } catch (RepositoryException e) {
            throw new LazyLoadingException(e.getMessage());
        }
    }

    @Override
    public void setProjectionList(List<Projection> projectionList) {
        isProjectionListLoaded = true;
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
