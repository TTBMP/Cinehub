package com.ttbmp.cinehub.service.persistence.utils.jdbc.dao.operation.strategies;

import com.ttbmp.cinehub.service.persistence.dao.CinemaDao;
import com.ttbmp.cinehub.service.persistence.CinemaDatabase;
import com.ttbmp.cinehub.service.persistence.dao.MovieDao;
import com.ttbmp.cinehub.service.persistence.entity.Cinema;
import com.ttbmp.cinehub.service.persistence.entity.Movie;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceClassException;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceMethodException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * @author Fabio Buracchi
 */
class DaoDeleteOperationTest {

    @Test
    void execute_DeleteDto_doesNotThrow() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException, DaoMethodException {
        CinemaDao dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getCinemaDao();
        Cinema dto = new Cinema(2, "pippo", "pluto", "paperino");
        dao.insert(dto);
        dto = dao.getCinema("pippo");
        Cinema finalDto = dto;
        assertDoesNotThrow(() -> dao.delete(finalDto));
    }

    @Test
    void prova() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException, DaoMethodException, IOException {
        MovieDao movieDao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getMovieDao();
        List<Movie> list = movieDao.getMovieList(Time.valueOf("12:00"));
        System.out.println(list);

    }

    @Test
    void execute_DeleteListDto_doesNotThrow() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException, DaoMethodException {
        CinemaDao dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getCinemaDao();
        List<Cinema> dtoList = Arrays.asList(
                new Cinema(2, "qui", "quo", "qua"),
                new Cinema(3, "paperino", "gastone", "paperoga")
        );
        dao.insert(dtoList);
        dtoList = Arrays.asList(
                dao.getCinema("qui"),
                dao.getCinema("paperino")
        );
        List<Cinema> finalDtoList = dtoList;
        assertDoesNotThrow(() -> dao.delete(finalDtoList));
    }



}