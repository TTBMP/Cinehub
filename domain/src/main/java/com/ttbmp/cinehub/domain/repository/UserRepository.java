package com.ttbmp.cinehub.domain.repository;

import com.ttbmp.cinehub.domain.entity.User;
import com.ttbmp.cinehub.domain.utilities.result.Result;

/**
 * @author Fabio Buracchi
 */
public interface UserRepository {

    Result<User> getUser(int userId);

}
