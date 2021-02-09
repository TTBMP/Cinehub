package com.ttbmp.cinehub.app.repository;

import com.ttbmp.cinehub.domain.User;

/**
 * @author Fabio Buracchi
 */
public interface UserRepository {

    User getUser(int userId);

}
