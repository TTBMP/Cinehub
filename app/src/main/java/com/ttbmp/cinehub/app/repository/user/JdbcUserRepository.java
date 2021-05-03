package com.ttbmp.cinehub.app.repository.user;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.domain.User;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;
import com.ttbmp.cinehub.service.persistence.CinemaDatabase;
import com.ttbmp.cinehub.service.persistence.dao.UserDao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

public class JdbcUserRepository implements UserRepository {
    private final ServiceLocator serviceLocator;

    private UserDao userDao = null;

    public JdbcUserRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public User getUser(String userId) throws RepositoryException {
        try {
            com.ttbmp.cinehub.service.persistence.entity.User user = null;
            user = getUserDao().getUserById(userId);
            return new UserProxy(user.getId(), user.getName(), user.getSurname(), user.getEmail());
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public User getUser(Ticket ticket) throws RepositoryException {
        try {
            var user = getUserDao().getUserByTicket(ticket.getId());
            return new UserProxy(user.getId(), user.getName(), user.getSurname(), user.getEmail());
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }

    }

    private UserDao getUserDao() {
        if (userDao == null) {
            try {
                this.userDao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getUserDao();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userDao;
    }

}
