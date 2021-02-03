package com.ttbmp.cinehub.data.local.mock;

import com.ttbmp.cinehub.core.entity.CreditCard;
import com.ttbmp.cinehub.core.entity.User;
import com.ttbmp.cinehub.core.repository.UserRepository;
import com.ttbmp.cinehub.core.utilities.result.Result;

import java.util.Arrays;
import java.util.List;

/**
 * @author Fabio Buracchi
 */
public class MockUserRepository implements UserRepository {

    private static final List<User> userList = Arrays.asList(
            new User(
                    "Ivan",
                    "palm@5934.c",
                    new CreditCard("22/24", 354, "4242424242424242", "5496")
            ),
            new User(
                    "Mattia",
                    "mahsdj@giggii.cosenodix",
                    new CreditCard("22/24", 354, "4242424242424242", "5496")
            )
    );

    @Override
    public Result<User> getUser(int userId) {
        return new Result<>(userList.get(userId));
    }

}
