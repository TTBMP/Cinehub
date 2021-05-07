package com.ttbmp.cinehub.service.persistence;

import com.ttbmp.cinehub.service.persistence.dao.*;
import com.ttbmp.cinehub.service.persistence.entity.*;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Database;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSource;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceMethodException;

/**
 * @author Fabio Buracchi
 */
@Database(
        version = 1,
        url = "mysql://localhost:3306/cinemadb",
        user = "admin",
        password = "admin",
        timezone = "Europe/Rome",
        driverClassName = "com.mysql.cj.jdbc.Driver",
        entities = {
                Cinema.class,
                Movie.class,
                Projection.class,
                Hall.class,
                Shift.class,
                Ticket.class,
                User.class,
                Seat.class,
                Employee.class,
                ProjectionistShift.class
        }
)

public interface CinemaDatabase extends JdbcDataSource {

    CinemaDao getCinemaDao() throws DataSourceMethodException;

    MovieDao getMovieDao() throws DataSourceMethodException;

    HallDao getHallDao() throws DataSourceMethodException;

    ShiftDao getShiftDao() throws DataSourceMethodException;

    TicketDao getTicketDao() throws DataSourceMethodException;

    UserDao getUserDao() throws DataSourceMethodException;

    SeatDao getSeatDao() throws DataSourceMethodException;

    ProjectionDao getProjectionDao() throws DataSourceMethodException;

    EmployeeDao getEmployeeDao() throws DataSourceMethodException;

    ProjectionistDao getProjectionistDao() throws DataSourceMethodException;

    UsherDao getUsherDao() throws DataSourceMethodException;

    ProjectionistShiftDao getProjectionistShiftDao() throws DataSourceMethodException;

    UsherShiftDao getUsherShiftDao() throws DataSourceMethodException;

}
