package com.ttbmp.cinehub.service.persistence.dao;

import com.ttbmp.cinehub.service.persistence.CinemaDatabase;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceClassException;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceMethodException;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * @author Ivan Palmieri, Massimo Mazzetti
 */

class ProjectionDaoTest {

    @Test
    void getProjectionListByCinemaAndMovieAndDate() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        ProjectionDao dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getProjectionDao();
        assertDoesNotThrow(() -> dao.getProjectionListByCinemaAndMovieAndDate(1, 3, "2020-02-15"));
    }

    @Test
    void getProjectionListByDate() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        ProjectionDao dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getProjectionDao();
        assertDoesNotThrow(() -> dao.getProjectionListByDate("2020-02-15"));
    }

    @Test
    void getProjectionListByDateAndMovie() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        ProjectionDao dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getProjectionDao();
        assertDoesNotThrow(() -> dao.getProjectionListByDateAndMovie(3, "2020-02-15"));
    }

    @Test
    void getProjectionListByProjectionist() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        ProjectionDao dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getProjectionDao();
        assertDoesNotThrow(() -> dao.getProjectionListByProjectionistShift(3));
    }


    @Test
    void getProjectionByDateAndTimeAndHallId() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        ProjectionDao dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getProjectionDao();
        assertDoesNotThrow(() -> dao.getProjectionByDateAndTimeAndHallId("2020-02-15", "12:00", 0));
    }

}
