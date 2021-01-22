package com.ttbmp.cinehub.core.repository;

import com.ttbmp.cinehub.core.entity.User;
import com.ttbmp.cinehub.core.utilities.result.Result;

/**
 * @author Fabio Buracchi
 */
public interface UserRepository {

    Result<User> getUser(int userId);

}
