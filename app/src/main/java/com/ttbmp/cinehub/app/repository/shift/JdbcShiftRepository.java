package com.ttbmp.cinehub.app.repository.shift;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.employee.projectionist.ProjectionistRepository;
import com.ttbmp.cinehub.app.repository.employee.usher.UsherRepository;
import com.ttbmp.cinehub.app.repository.hall.HallRepository;
import com.ttbmp.cinehub.app.repository.projection.ProjectionRepository;
import com.ttbmp.cinehub.app.repository.shift.projectionist.ProjectionistShiftProxy;
import com.ttbmp.cinehub.app.repository.shift.usher.UsherShiftProxy;
import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.employee.Projectionist;
import com.ttbmp.cinehub.domain.employee.Usher;
import com.ttbmp.cinehub.domain.shift.Shift;
import com.ttbmp.cinehub.service.persistence.CinemaDatabase;
import com.ttbmp.cinehub.service.persistence.dao.ShiftDao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceClassException;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceMethodException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
            var employeeDao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getEmployeeDao();
            if (employeeDao.getEmployeeByShiftId(shiftId).getRole().equals("maschera")) {
                return new UsherShiftProxy(
                        shift.getId(),
                        shift.getDate(),
                        shift.getStart(),
                        shift.getEnd(),
                        serviceLocator.getService(UsherRepository.class)
                );
            } else if (employeeDao.getEmployeeByShiftId(shiftId).getRole().equals("proiezionista")) {
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
                return null;
            }
        } catch (DaoMethodException | DataSourceClassException | SQLException | ClassNotFoundException | DataSourceMethodException e) {
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
            if (employee instanceof Projectionist) {
                return new ProjectionistShiftProxy(
                        shift.getId(),
                        shift.getDate(),
                        shift.getStart(),
                        shift.getEnd(),
                        serviceLocator.getService(ProjectionistRepository.class),
                        serviceLocator.getService(HallRepository.class),
                        serviceLocator.getService(ProjectionRepository.class));
            } else if (employee instanceof Usher) {
                return new UsherShiftProxy(
                        shift.getId(),
                        shift.getDate(),
                        shift.getStart(),
                        shift.getEnd(),
                        serviceLocator.getService(UsherRepository.class)
                );
            } else {
                return null;
            }
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
        try {
            var employeeDao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getEmployeeDao();
            List<Shift> allShiftList = new ArrayList<>();
            for (var shift : shiftList) {
                if (employeeDao.getEmployeeByShiftId(shift.getId()).getRole().equals("maschera")) {
                    allShiftList.add(new UsherShiftProxy(
                            shift.getId(),
                            shift.getDate(),
                            shift.getStart(),
                            shift.getEnd(),
                            serviceLocator.getService(UsherRepository.class)));
                } else if (employeeDao.getEmployeeByShiftId(shift.getId()).getRole().equals("proiezionista")) {
                    allShiftList.add(new ProjectionistShiftProxy(
                            shift.getId(),
                            shift.getDate(),
                            shift.getStart(),
                            shift.getEnd(),
                            serviceLocator.getService(ProjectionistRepository.class),
                            serviceLocator.getService(HallRepository.class),
                            serviceLocator.getService(ProjectionRepository.class)));
                }
            }
            return allShiftList;
        } catch (DataSourceMethodException | DataSourceClassException | SQLException | ClassNotFoundException | DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

}
