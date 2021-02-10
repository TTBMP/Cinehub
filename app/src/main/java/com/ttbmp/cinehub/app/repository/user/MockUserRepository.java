package com.ttbmp.cinehub.app.repository.user;

import com.ttbmp.cinehub.app.repository.user.UserRepository;
import com.ttbmp.cinehub.domain.CreditCard;
import com.ttbmp.cinehub.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Fabio Buracchi
 */
public class MockUserRepository implements UserRepository {

    private static final List<User> userList = new ArrayList<>();

    static {
        userList.add(new User(
                0,
                "Fabio",
                "Buracchi",
                "fb@cinehub.com",
                new CreditCard("4242424242424242", 354, "22/24")
        ));
        userList.add(new User(
                1,
                "Massimo",
                "Mazzetti",
                "mm@cinehub.com",
                new CreditCard("4242424242424242", 354, "22/24")
        ));
        userList.add(new User(
                2,
                "Ivan",
                "Palmieri",
                "ip@cinehub.com",
                new CreditCard("4242424242424242", 354, "22/24")
        ));
        userList.add(new User(
                3,
                "Mario",
                "Rossi",
                "mr@cinehub.com",
                new CreditCard("4242424242424242", 354, "22/24")
        ));
    }

    @Override
    public User getUser(int userId) {
        return userList.get(userId);
    }

}
