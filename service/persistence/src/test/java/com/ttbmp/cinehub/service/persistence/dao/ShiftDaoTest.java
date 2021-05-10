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
        var dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getShiftDao();
        assertDoesNotThrow(()->dao.getCinemaShiftListBetween(1, "2021-05-10", "2021-05-15"));
    }

    @Test
    void getShiftById() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        var dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getShiftDao();
        assertDoesNotThrow(() -> dao.getShiftById(2));
    }

    @Test
    void getShiftListByEmployeeId() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        var dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getShiftDao();
        assertDoesNotThrow(() -> dao.getShiftListByEmployeeId("0"));
    }

}
