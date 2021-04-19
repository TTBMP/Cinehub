package com.ttbmp.cinehub.service.persistence.dao;

import com.ttbmp.cinehub.service.persistence.CinemaDatabase;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceClassException;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceMethodException;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
/**
 * @author Ivan Palmieri, Massimo Mazzetti
 */
class SeatDaoTest {

   @Test
    void getSeatListByHallId() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        SeatDao dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getSeatDao();
        assertDoesNotThrow(()->dao.getSeatList(0));

    }

    @Test
    void getSeatByTicketId() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        SeatDao dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getSeatDao();
        assertDoesNotThrow(()->dao.getSeatByTicketId(5));

    }
}