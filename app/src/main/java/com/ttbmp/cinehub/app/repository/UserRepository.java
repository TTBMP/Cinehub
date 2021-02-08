package com.ttbmp.cinehub.app.repository;

import com.ttbmp.cinehub.domain.User;
import com.ttbmp.cinehub.app.utilities.result.Result;

/**
 * @author Fabio Buracchi
 */
public interface UserRepository {

    Result<User> getUser(int userId);

}
