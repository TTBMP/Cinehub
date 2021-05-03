package com.ttbmp.cinehub.service.persistence.dao;

import com.ttbmp.cinehub.service.persistence.CinemaDatabase;
import com.ttbmp.cinehub.service.persistence.entity.Ticket;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceClassException;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceMethodException;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * @author Ivan Palmieri, , Massimo Mazzetti
 */
class TicketDaoTest {

    @Test
    void getTicketList() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        TicketDao dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getTicketDao();
        assertDoesNotThrow(() -> dao.getTicketList(1));
    }

    @Test
    void execute_SaveTicket_doesNotThrow() throws DataSourceClassException, SQLException, ClassNotFoundException, DataSourceMethodException {
        TicketDao dao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getTicketDao();
        assertDoesNotThrow(() -> dao.insert(new Ticket(0, 1, 1, "0", 2)));
    }

}
