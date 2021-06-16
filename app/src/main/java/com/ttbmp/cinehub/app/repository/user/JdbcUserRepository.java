package com.ttbmp.cinehub.app.repository.user;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.utilities.repository.JdbcRepository;
import com.ttbmp.cinehub.domain.User;
import com.ttbmp.cinehub.domain.security.Role;
import com.ttbmp.cinehub.service.persistence.dao.RoleDao;
import com.ttbmp.cinehub.service.persistence.dao.UserDao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import java.util.stream.Collectors;

public class JdbcUserRepository extends JdbcRepository implements UserRepository {

    public JdbcUserRepository(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public User getUser(String userId) throws RepositoryException {
        try {
            var user = getDao(UserDao.class).getUserById(userId);
            var roleList = getDao(RoleDao.class).getRoleList(userId).stream()
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
            return new UserProxy(getServiceLocator(), user.getId(), user.getName(), user.getSurname(), user.getEmail(), roleList);
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

}
