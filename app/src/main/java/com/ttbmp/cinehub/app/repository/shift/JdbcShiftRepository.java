package com.ttbmp.cinehub.app.repository.shift;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.cinema.CinemaRepository;
import com.ttbmp.cinehub.app.repository.creditcard.CreditCardRepository;
import com.ttbmp.cinehub.app.repository.employee.JdbcEmployeeRepository;
import com.ttbmp.cinehub.app.repository.employee.projectionist.ProjectionistProxy;
import com.ttbmp.cinehub.app.repository.employee.projectionist.ProjectionistRepository;
import com.ttbmp.cinehub.app.repository.employee.usher.UsherProxy;
import com.ttbmp.cinehub.app.repository.employee.usher.UsherRepository;
import com.ttbmp.cinehub.app.repository.hall.HallRepository;
import com.ttbmp.cinehub.app.repository.projection.ProjectionRepository;
import com.ttbmp.cinehub.app.repository.seat.SeatRepository;
import com.ttbmp.cinehub.app.repository.shift.projectionist.ProjectionistShiftProxy;
import com.ttbmp.cinehub.app.repository.shift.usher.UsherShiftProxy;
import com.ttbmp.cinehub.app.repository.ticket.TicketProxy;
import com.ttbmp.cinehub.app.repository.user.UserRepository;
import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.shift.Shift;
import com.ttbmp.cinehub.service.persistence.CinemaDatabase;
import com.ttbmp.cinehub.service.persistence.dao.EmployeeDao;
import com.ttbmp.cinehub.service.persistence.dao.ShiftDao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceClassException;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceMethodException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class JdbcShiftRepository implements ShiftRepository {

    private final ServiceLocator serviceLocator;

    private ShiftDao shiftDao = null;

    public JdbcShiftRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public Shift getShift(int shiftId) throws RepositoryException {
        try {
            com.ttbmp.cinehub.service.persistence.entity.Shift shift = getShiftDao().getShiftById(shiftId);
            EmployeeDao employeeDao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getEmployeeDao();
            if(employeeDao.getEmployeeByShiftId(shiftId).getRole().equals("maschera")){
                return new UsherShiftProxy(
                        shift.getId(),
                        shift.getDate(),
                        shift.getStart(),
                        shift.getEnd(),
                        serviceLocator.getService(UsherRepository.class)
                );
            }
            else if(employeeDao.getEmployeeByShiftId(shiftId).getRole().equals("proiezionista")){
                return new ProjectionistShiftProxy(
                        shift.getId(),
                        shift.getDate(),
                        shift.getStart(),
                        shift.getEnd(),
                        serviceLocator.getService(ProjectionistRepository.class),
                        serviceLocator.getService(HallRepository.class),
                        serviceLocator.getService(ProjectionRepository.class)
                );
            }
            else{
                return null;
            }
        } catch (DaoMethodException | DataSourceClassException | SQLException | ClassNotFoundException | DataSourceMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public List<Shift> getShiftList() throws RepositoryException {
        try {
            List<com.ttbmp.cinehub.service.persistence.entity.Shift> shiftList = getShiftDao().getShiftList();
            EmployeeDao employeeDao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getEmployeeDao();
            
            if(employeeDao.getEmployeeByShiftId(shiftList.get(0).getId()).getRole().equals("maschera")){
                return shiftList.stream()
                        .map(shift ->  new UsherShiftProxy(
                                shift.getId(),
                                shift.getDate(),
                                shift.getStart(),
                                shift.getEnd(),
                                serviceLocator.getService(UsherRepository.class)))
                        .collect(Collectors.toList());
            }
            else if(employeeDao.getEmployeeByShiftId(shiftList.get(0).getId()).getRole().equals("proiezionista")){
                return shiftList.stream()
                        .map(shift ->  new ProjectionistShiftProxy(
                                shift.getId(),
                                shift.getDate(),
                                shift.getStart(),
                                shift.getEnd(),
                                serviceLocator.getService(ProjectionistRepository.class),
                                serviceLocator.getService(HallRepository.class),
                                serviceLocator.getService(ProjectionRepository.class)))
                        .collect(Collectors.toList());
            }
            else{
                return null;
            }
        } catch (DaoMethodException | DataSourceClassException | SQLException | ClassNotFoundException | DataSourceMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public List<Shift> getShiftList(Employee employee) throws RepositoryException {
        try {
            List<com.ttbmp.cinehub.service.persistence.entity.Shift> shiftList = getShiftDao().getShiftListByEmployeeId(employee.getId());
            EmployeeDao employeeDao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getEmployeeDao();
            if(employeeDao.getEmployeeByShiftId(shiftList.get(0).getId()).getRole().equals("maschera")){
                return shiftList.stream()
                        .map(shift ->  new UsherShiftProxy(
                        shift.getId(),
                        shift.getDate(),
                        shift.getStart(),
                        LocalTime.parse(shift.getEnd()).minusHours(1).toString(),
                        serviceLocator.getService(UsherRepository.class)))
                    .collect(Collectors.toList());
            }
            else if(employeeDao.getEmployeeByShiftId(shiftList.get(0).getId()).getRole().equals("proiezionista")){
                return shiftList.stream()
                        .map(shift ->  new ProjectionistShiftProxy(
                                shift.getId(),
                                shift.getDate(),
                                shift.getStart(),
                                LocalTime.parse(shift.getEnd()).minusHours(1).toString(),
                                serviceLocator.getService(ProjectionistRepository.class),
                                serviceLocator.getService(HallRepository.class),
                                serviceLocator.getService(ProjectionRepository.class)))
                        .collect(Collectors.toList());
            }
            else{
                return null;
            }
        } catch (DaoMethodException | DataSourceClassException | SQLException | ClassNotFoundException | DataSourceMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }




    @Override
    public void saveShift(Shift shift) throws DaoMethodException {
        getShiftDao().insert(new com.ttbmp.cinehub.service.persistence.entity.Shift(
                shift.getId(),
                shift.getStart(),
                shift.getEnd(),
                shift.getEmployee().getId(),
                shift.getDate()
                ));
    }

    @Override
    public void deletedShift(Shift shift) throws DaoMethodException {
        getShiftDao().delete(new com.ttbmp.cinehub.service.persistence.entity.Shift(
                shift.getId(),
                shift.getStart(),
                shift.getEnd(),
                shift.getEmployee().getId(),
                shift.getDate()
        ));
    }

    @Override
    public void modifyShift(Shift shift) throws DaoMethodException {
        getShiftDao().update(new com.ttbmp.cinehub.service.persistence.entity.Shift(
                shift.getId(),
                shift.getStart(),
                shift.getEnd(),
                shift.getEmployee().getId(),
                shift.getDate()
        ));
    }

    @Override
    public List<Shift> getAllEmployeeShiftBetweenDate(Employee employee, LocalDate start, LocalDate end) {
        return null;
    }

    private ShiftDao getShiftDao() {
        if (shiftDao == null) {
            try {
                this.shiftDao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getShiftDao();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return shiftDao;
    }
}
