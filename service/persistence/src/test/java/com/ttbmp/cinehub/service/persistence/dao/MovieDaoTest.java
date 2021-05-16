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

class MovieDaoTest {

    @Test
    void getAllMovie() {
    }

    @Test
    void getMovieList() {
    }

    @Test
    void getMovie() {
    }

    @Test
    void getMovieById() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        var dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getMovieDao();
        assertDoesNotThrow(() -> dao.getMovieById(3));
    }

    @Test
    void getMovieByData() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        var dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getMovieDao();
        assertDoesNotThrow(() -> dao.getMovieByData("2020-02-15"));
    }

    @Test
    void getMovieByProjection() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        var dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getMovieDao();
        assertDoesNotThrow(() -> dao.getMovieByProjection(1));
    }

}
