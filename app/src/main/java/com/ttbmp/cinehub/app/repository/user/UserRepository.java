package com.ttbmp.cinehub.app.repository.user;

import com.ttbmp.cinehub.domain.User;

/**
 * @author Fabio Buracchi
 */
public interface UserRepository {

    User getUser(String userId);

}
