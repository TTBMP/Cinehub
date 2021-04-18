package com.ttbmp.cinehub.service.persistence.dao;

import com.ttbmp.cinehub.service.persistence.CinemaDatabase;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceClassException;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceMethodException;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ivan Palmieri
 */

class CinemaDaoTest {

    @Test
    void getAllCinema() {
    }

    @Test
    void getCinemaById() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        CinemaDao dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getCinemaDao();
        assertDoesNotThrow(()->dao.getCinemaById(1));
    }

    @Test
    void getCinemaByMovieIdAndDate() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        CinemaDao dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getCinemaDao();
        assertDoesNotThrow(()->dao.getCinemaByMovieIdAndDate(3,"2020-02-15"));
    }

    @Test
    void getCinemaByProjection() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        CinemaDao dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getCinemaDao();
        assertDoesNotThrow(()->dao.getCinemaByProjection(1));

    }

    @Test
    void getCinema() {
    }
}