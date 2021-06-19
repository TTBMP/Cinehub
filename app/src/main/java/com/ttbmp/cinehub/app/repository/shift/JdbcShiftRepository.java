package com.ttbmp.cinehub.app.repository.shift;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.shift.projectionist.ProjectionistShiftProxy;
import com.ttbmp.cinehub.app.repository.shift.usher.UsherShiftProxy;
import com.ttbmp.cinehub.app.repository.user.UserRepository;
import com.ttbmp.cinehub.app.utilities.repository.JdbcRepository;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.security.Role;
import com.ttbmp.cinehub.domain.shift.Shift;
import com.ttbmp.cinehub.service.persistence.dao.ShiftDao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcShiftRepository extends JdbcRepository implements ShiftRepository {

    public JdbcShiftRepository(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public Shift getShift(int shiftId) throws RepositoryException {
        try {
            var shift = getDao(ShiftDao.class).getShiftById(shiftId);
            return getShift(shift);
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public List<Shift> getCinemaShiftListBetween(Cinema cinema, LocalDate start, LocalDate end) throws RepositoryException {
        try {
            var shiftList = getDao(ShiftDao.class).getCinemaShiftListBetween(cinema.getId(), start.toString(), end.toString());
            return getListShift(shiftList);
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }


    @Override
    public List<Shift> getShiftList(Employee employee) throws RepositoryException {
        try {
            var shiftList = getDao(ShiftDao.class).getShiftListByEmployeeId(employee.getId());
            return getListShift(shiftList);
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public List<Shift> getAllEmployeeShiftBetweenDate(Employee employee, LocalDate start, LocalDate end) throws RepositoryException {
        try {
            var shiftList = getDao(ShiftDao.class).getShiftBetweenDate(employee.getId(), String.valueOf(start), String.valueOf(end));
            return getListShift(shiftList);
        } catch (DaoMethodException | RepositoryException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public Shift getShift(Employee employee, String date, String start, String end) throws RepositoryException {
        try {
            var shift = getDao(ShiftDao.class).getShift(employee.getId(), date, start, end);
            return getShift(shift);
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public void saveShift(Shift shift) throws RepositoryException {
        try {
            getDao(ShiftDao.class).insert(new com.ttbmp.cinehub.service.persistence.entity.Shift(
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
            getDao(ShiftDao.class).delete(getDao(ShiftDao.class).getShiftById(shift.getId()));
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public void modifyShift(Shift shift) throws RepositoryException {
        try {
            getDao(ShiftDao.class).update(new com.ttbmp.cinehub.service.persistence.entity.Shift(
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

    private List<Shift> getListShift(List<com.ttbmp.cinehub.service.persistence.entity.Shift> shiftList) throws RepositoryException {
        List<Shift> allShiftList = new ArrayList<>();
        for (var shift : shiftList) {
            allShiftList.add(getShift(shift));
        }
        return allShiftList;
    }

    private Shift getShift(com.ttbmp.cinehub.service.persistence.entity.Shift shift) throws RepositoryException {
        var roleList = getServiceLocator().getService(UserRepository.class).getUser(shift.getUserId()).getRoleList();
        if (roleList.contains(Role.USHER_ROLE)) {
            return new UsherShiftProxy(getServiceLocator(), shift.getId(), shift.getDate(), shift.getStart(), shift.getEnd());
        } else if (roleList.contains(Role.PROJECTIONIST_ROLE)) {
            return new ProjectionistShiftProxy(getServiceLocator(), shift.getId(), shift.getDate(), shift.getStart(), shift.getEnd());
        } else {
            throw new RepositoryException("Not an employee.");
        }
    }

}
