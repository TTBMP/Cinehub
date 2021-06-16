package com.ttbmp.cinehub.app.repository.customer;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.utilities.repository.JdbcRepository;
import com.ttbmp.cinehub.domain.Customer;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;
import com.ttbmp.cinehub.service.persistence.dao.UserDao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

public class JdbcCustomerRepository extends JdbcRepository implements CustomerRepository {

    public JdbcCustomerRepository(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public Customer getCustomer(String userId) throws RepositoryException {
        return new CustomerProxy(getServiceLocator(), userId);
    }

    @Override
    public Customer getCustomer(Ticket ticket) throws RepositoryException {
        try {
            var user = getDao(UserDao.class).getUserByTicket(ticket.getId());
            return new CustomerProxy(getServiceLocator(), user.getId());
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

}
