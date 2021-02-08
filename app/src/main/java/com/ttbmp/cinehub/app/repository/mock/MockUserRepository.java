package com.ttbmp.cinehub.app.repository.mock;

import com.ttbmp.cinehub.domain.CreditCard;
import com.ttbmp.cinehub.domain.User;
import com.ttbmp.cinehub.app.repository.UserRepository;
import com.ttbmp.cinehub.app.utilities.result.Result;

import java.util.Arrays;
import java.util.List;

/**
 * @author Fabio Buracchi
 */
public class MockUserRepository implements UserRepository {

    private static final List<User> userList = Arrays.asList(
            new User(
                    0,
                    "Ivan",
                    "palm@5934.c",
                    new CreditCard("22/24", 354, "4242424242424242", "5496")
            ),
            new User(
                    1,
                    "Mattia",
                    "mahsdj@giggii.cosedfsnodix",
                    new CreditCard("22/24", 354, "4242424242424242", "5496")
            )
    );

    @Override
    public Result<User> getUser(int userId) {
        return new Result<>(userList.get(userId));
    }

}
