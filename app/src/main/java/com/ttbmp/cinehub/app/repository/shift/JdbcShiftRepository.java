package com.ttbmp.cinehub.app.repository.shift;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.employee.projectionist.ProjectionistRepository;
import com.ttbmp.cinehub.app.repository.employee.usher.UsherRepository;
import com.ttbmp.cinehub.app.repository.hall.HallRepository;
import com.ttbmp.cinehub.app.repository.projection.ProjectionRepository;
import com.ttbmp.cinehub.app.repository.shift.projectionist.ProjectionistShiftProxy;
import com.ttbmp.cinehub.app.repository.shift.usher.UsherShiftProxy;
import com.ttbmp.cinehub.app.repository.user.UserRepository;
import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.security.Role;
import com.ttbmp.cinehub.domain.shift.Shift;
import com.ttbmp.cinehub.service.persistence.CinemaDatabase;
import com.ttbmp.cinehub.service.persistence.dao.ShiftDao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JdbcShiftRepository implements ShiftRepository {

    private final ServiceLocator serviceLocator;

    private ShiftDao shiftDao = null;

    public JdbcShiftRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public Shift getShift(int shiftId) throws RepositoryException {
        try {
            var shift = getShiftDao().getShiftById(shiftId);
            return getShift(shift);
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public List<Shift> getCinemaShiftListBetween(int cinema, LocalDate start, LocalDate end) throws RepositoryException {
        try {
            var shiftList = getShiftDao().getCinemaShiftListBetween(cinema, start.toString(), end.toString());
            return getListShift(shiftList);
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }


    @Override
    public List<Shift> getShiftList(Employee employee) throws RepositoryException {
        try {
            var shiftList = getShiftDao().getShiftListByEmployeeId(employee.getId());
            return getListShift(shiftList);
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public List<Shift> getAllEmployeeShiftBetweenDate(Employee employee, LocalDate start, LocalDate end) throws RepositoryException {
        try {
            var shiftList = getShiftDao().getShiftBetweenDate(employee.getId(), String.valueOf(start), String.valueOf(end));
            return getListShift(shiftList);
        } catch (DaoMethodException | RepositoryException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public Shift getShift(Employee employee, String date, String start, String end) throws RepositoryException {
        try {
            var shift = getShiftDao().getShift(employee.getId(), date, start, end);
            return getShift(shift);
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public void saveShift(Shift shift) throws RepositoryException {
        try {
            getShiftDao().insert(new com.ttbmp.cinehub.service.persistence.entity.Shift(
                    shift.getId(),
                    shift.getStart(),
                    shift.getEnd(),
                    shift.getEmployee().getId(),
                    shift.getDate()
            ));
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public void deletedShift(Shift shift) throws RepositoryException {
        try {
            getShiftDao().delete(getShiftDao().getShiftById(shift.getId()));
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public void modifyShift(Shift shift) throws RepositoryException {
        try {
            getShiftDao().update(new com.ttbmp.cinehub.service.persistence.entity.Shift(
                    shift.getId(),
                    shift.getStart(),
                    shift.getEnd(),
                    shift.getEmployee().getId(),
                    shift.getDate()
            ));
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    private ShiftDao getShiftDao() throws RepositoryException {
        if (shiftDao == null) {
            try {
                this.shiftDao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getShiftDao();
            } catch (Exception e) {
                throw new RepositoryException(e.getMessage());
            }
        }
        return shiftDao;
    }

    private List<Shift> getListShift(List<com.ttbmp.cinehub.service.persistence.entity.Shift> shiftList) throws RepositoryException {
        List<Shift> allShiftList = new ArrayList<>();
        for (var shift : shiftList) {
            allShiftList.add(getShift(shift));
        }
        return allShiftList;
    }

    private Shift getShift(com.ttbmp.cinehub.service.persistence.entity.Shift shift) throws RepositoryException {
        var roleList = Arrays.asList(serviceLocator.getService(UserRepository.class).getUser(shift.getUserId()).getRoles());
        if (roleList.contains(Role.USHER_ROLE)) {
            return new UsherShiftProxy(
                    shift.getId(),
                    shift.getDate(),
                    shift.getStart(),
                    shift.getEnd(),
                    serviceLocator.getService(UsherRepository.class)
            );
        } else if (roleList.contains(Role.PROJECTIONIST_ROLE)) {
            return new ProjectionistShiftProxy(
                    shift.getId(),
                    shift.getDate(),
                    shift.getStart(),
                    shift.getEnd(),
                    serviceLocator.getService(ProjectionistRepository.class),
                    serviceLocator.getService(HallRepository.class),
                    serviceLocator.getService(ProjectionRepository.class)
            );
        } else {
            throw new RepositoryException("Not an employee.");
        }
    }

}
