package com.ttbmp.cinehub.app.repository.user;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.domain.User;
import com.ttbmp.cinehub.service.persistence.CinemaDatabase;
import com.ttbmp.cinehub.service.persistence.dao.RoleDao;
import com.ttbmp.cinehub.service.persistence.dao.UserDao;
import com.ttbmp.cinehub.service.persistence.entity.Role;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import java.util.stream.Collectors;

public class JdbcUserRepository implements UserRepository {

    private final ServiceLocator serviceLocator;

    private UserDao userDao = null;
    private RoleDao roleDao = null;

    public JdbcUserRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public User getUser(String userId) throws RepositoryException {
        try {
            var user = getUserDao().getUserById(userId);
            var roleList = getRoleDao().getRoleList(userId);
            var rolesString = roleList.stream()
                    .map(Role::getName)
                    .collect(Collectors.joining(";"));
            return new UserProxy(
                    user.getId(),
                    user.getName(),
                    user.getSurname(),
                    user.getEmail(),
                    rolesString
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

    private RoleDao getRoleDao() throws RepositoryException {
        if (roleDao == null) {
            try {
                this.roleDao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getRoleDao();
            } catch (Exception e) {
                throw new RepositoryException(e.getMessage());
            }
        }
        return roleDao;
    }

}
