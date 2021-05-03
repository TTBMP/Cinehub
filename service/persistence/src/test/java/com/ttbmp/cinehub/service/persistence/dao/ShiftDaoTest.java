package com.ttbmp.cinehub.service.persistence.dao;

import com.ttbmp.cinehub.service.persistence.CinemaDatabase;
import com.ttbmp.cinehub.service.persistence.entity.Shift;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceClassException;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceMethodException;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * @author Ivan Palmieri, Massimo Mazzetti
 */
class ShiftDaoTest {

    @Test
    void getShiftList() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        ShiftDao dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getShiftDao();
        assertDoesNotThrow(dao::getShiftList);
    }

    @Test
    void getShiftById() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        ShiftDao dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getShiftDao();
        assertDoesNotThrow(() -> dao.getShiftById(2));
    }

    @Test
    void getShiftListByEmployeeId() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        ShiftDao dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getShiftDao();
        assertDoesNotThrow(() -> dao.getShiftListByEmployeeId("0"));
    }

    @Test
    void saveShift() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException, DaoMethodException {
        ShiftDao dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getShiftDao();
        assertDoesNotThrow(() -> dao.insert(new Shift(0, "12:00", "16:00", "0", "2020-02-15")));
    }

    @Test
    void deleteShift() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException, DaoMethodException {
        ShiftDao dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getShiftDao();
        assertDoesNotThrow(() -> dao.delete(dao.getShiftById(2)));
    }

    @Test
    void modifyShift() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException, DaoMethodException {
        ShiftDao dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getShiftDao();
        assertDoesNotThrow(() -> dao.update(new Shift(2, "12:00", "16:00", "0", "2010-02-10")));
    }

}
