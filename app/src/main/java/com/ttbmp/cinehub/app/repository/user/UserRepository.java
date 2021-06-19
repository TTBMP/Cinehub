package com.ttbmp.cinehub.app.repository.user;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.domain.User;

import java.util.List;

/**
 * @author Fabio Buracchi
 */
public interface UserRepository {

    User getUser(String userId) throws RepositoryException;

    List<User> getAllUser() throws RepositoryException;

}
