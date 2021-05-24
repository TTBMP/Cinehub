package com.ttbmp.cinehub.app.repository.customer;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.ticket.TicketRepository;
import com.ttbmp.cinehub.app.repository.user.UserRepository;
import com.ttbmp.cinehub.domain.Customer;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;
import com.ttbmp.cinehub.service.persistence.CinemaDatabase;
import com.ttbmp.cinehub.service.persistence.dao.UserDao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

public class JdbcCustomerRepository implements CustomerRepository {

    private final ServiceLocator serviceLocator;

    private UserDao userDao = null;

    public JdbcCustomerRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public Customer getCustomer(String userId) throws RepositoryException {
        return new CustomerProxy(
                userId,
                serviceLocator.getService(UserRepository.class),
                serviceLocator.getService(TicketRepository.class)
        );
    }

    @Override
    public Customer getCustomer(Ticket ticket) throws RepositoryException {
        try {
            var user = getUserDao().getUserByTicket(ticket.getId());
            return new CustomerProxy(
                    user.getId(),
                    serviceLocator.getService(UserRepository.class),
                    serviceLocator.getService(TicketRepository.class)
            );
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    private UserDao getUserDao() throws RepositoryException {
        if (userDao == null) {
            try {
                this.userDao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getUserDao();
            } catch (Exception e) {
                throw new RepositoryException(e.getMessage());
            }
        }
        return userDao;
    }

}
