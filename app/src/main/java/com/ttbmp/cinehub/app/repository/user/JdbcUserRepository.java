package com.ttbmp.cinehub.app.repository.user;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.domain.User;
import com.ttbmp.cinehub.domain.security.Role;
import com.ttbmp.cinehub.service.persistence.CinemaDatabase;
import com.ttbmp.cinehub.service.persistence.dao.RoleDao;
import com.ttbmp.cinehub.service.persistence.dao.UserDao;
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
            var roleList = getRoleDao().getRoleList(userId).stream()
                    .map(role -> {
                        switch (role.getName()) {
                            case "cliente":
                                return Role.CUSTOMER_ROLE;
                            case "dipendente":
                                return Role.EMPLOYEE_ROLE;
                            case "maschera":
                                return Role.USHER_ROLE;
                            case "proiezionista":
                                return Role.PROJECTIONIST_ROLE;
                            case "manager":
                                return Role.MANAGER_ROLE;
                            default:
                                throw new IllegalStateException("Unexpected value: " + role.getName());
                        }
                    })
                    .collect(Collectors.toList());
            return new UserProxy(serviceLocator, user.getId(), user.getName(), user.getSurname(), user.getEmail(), roleList);
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
