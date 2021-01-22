package com.ttbmp.cinehub.data.local.mock;

import com.ttbmp.cinehub.core.entity.User;
import com.ttbmp.cinehub.core.repository.UserRepository;
import com.ttbmp.cinehub.core.utilities.result.Result;

/**
 * @author Fabio Buracchi
 */
public class MockUserRepository implements UserRepository {

    @Override
    public Result<User> getUser(int userId) {
        return new Result<>(new User(userId));
    }

}
