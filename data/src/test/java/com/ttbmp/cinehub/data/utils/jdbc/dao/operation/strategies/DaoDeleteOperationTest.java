package com.ttbmp.cinehub.data.utils.jdbc.dao.operation.strategies;

import com.ttbmp.cinehub.data.CinemaDao;
import com.ttbmp.cinehub.data.CinemaDatabase;
import com.ttbmp.cinehub.data.CinemaDto;
import com.ttbmp.cinehub.data.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.data.utils.jdbc.exception.DaoMethodException;
import com.ttbmp.cinehub.data.utils.jdbc.exception.DataSourceClassException;
import com.ttbmp.cinehub.data.utils.jdbc.exception.DataSourceMethodException;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class DaoDeleteOperationTest {

    @Test
    void execute_DeleteDto_doesNotThrow() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException, DaoMethodException {
        CinemaDao dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getCinemaDao();
        CinemaDto dto = new CinemaDto(1, "pippo", "pluto", "paperino");
        dao.insert(dto);
        dto = dao.getCinema("pippo");
        CinemaDto finalDto = dto;
        assertDoesNotThrow(() -> dao.delete(finalDto));
    }

    @Test
    void execute_DeleteListDto_doesNotThrow() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException, DaoMethodException {
        CinemaDao dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getCinemaDao();
        List<CinemaDto> dtoList = Arrays.asList(
                new CinemaDto(2, "qui", "quo", "qua"),
                new CinemaDto(3, "paperino", "gastone", "paperoga")
        );
        dao.insert(dtoList);
        dtoList = Arrays.asList(
                dao.getCinema("qui"),
                dao.getCinema("paperino")
        );
        List<CinemaDto> finalDtoList = dtoList;
        assertDoesNotThrow(() -> dao.delete(finalDtoList));
    }

}