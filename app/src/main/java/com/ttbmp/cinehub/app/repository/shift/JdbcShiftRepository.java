package com.ttbmp.cinehub.app.repository.shift;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.seat.SeatRepository;
import com.ttbmp.cinehub.app.repository.ticket.TicketProxy;
import com.ttbmp.cinehub.app.repository.user.UserRepository;
import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.shift.Shift;
import com.ttbmp.cinehub.service.persistence.CinemaDatabase;
import com.ttbmp.cinehub.service.persistence.dao.ShiftDao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class JdbcShiftRepository implements ShiftRepository {

    private final ServiceLocator serviceLocator;

    private ShiftDao shiftDao = null;

    public JdbcShiftRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public Shift getShift(int shiftId) {
        return null;
    }

    @Override
    public List<Shift> getShiftList() {
       return null;
    }

    @Override
    public List<Shift> getShiftList(Employee employee) {
        return null;
    }

    @Override
    public void saveShift(Shift shift) {

    }

    @Override
    public void deletedShift(Shift shift)  {

    }

    @Override
    public void modifyShift(Shift shift)  {

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
