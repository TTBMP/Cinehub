package com.ttbmp.cinehub.service.persistence.utils.jdbc.dao.operation.strategies;

import com.ttbmp.cinehub.service.persistence.CinemaDao;
import com.ttbmp.cinehub.service.persistence.CinemaDatabase;
import com.ttbmp.cinehub.service.persistence.CinemaDto;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceClassException;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceMethodException;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * @author Fabio Buracchi
 */
class DaoUpdateOperationTest {

    @Test
    void execute_UpdateDto_doesNotThrow() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException, DaoMethodException {
        CinemaDao dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getCinemaDao();
        CinemaDto dto = new CinemaDto(1, "pippo", "pluto", "paperino");
        dao.insert(dto);
        dto = dao.getCinema("pippo");
        dto.setName("pluto");
        CinemaDto finalDto = dto;
        assertDoesNotThrow(() -> dao.update(finalDto));
    }

    @Test
    void execute_UpdateListDto_doesNotThrow() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException, DaoMethodException {
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
        assertDoesNotThrow(() -> dao.update(finalDtoList));
    }

}