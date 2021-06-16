package com.ttbmp.cinehub.app.service.security;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.user.MockUserRepository;
import com.ttbmp.cinehub.app.repository.user.UserRepository;
import com.ttbmp.cinehub.app.utilities.repository.MockRepository;
import com.ttbmp.cinehub.domain.User;

import java.util.Objects;

/**
 * @author Fabio Buracchi
 */
public class MockSecurityService implements SecurityService {

    private final UserRepository userRepository;

    public MockSecurityService(ServiceLocator serviceLocator) {
        this.userRepository = serviceLocator.getService(UserRepository.class);
    }

    @Override
    public String authenticate(String email, String password) throws SecurityException {
        return MockRepository.getMockDataList(MockUserRepository.class).stream()
                .filter(userData -> userData.get(MockUserRepository.EMAIL).equals(email))
                .map(userData -> userData.get(MockUserRepository.ID))
                .findAny()
                .orElseThrow(() -> new SecurityException("Wrong username or password."));
    }

    @Override
    public User authenticate(String sessionToken) throws SecurityException {
        try {
            var user = userRepository.getUser(sessionToken);
            Objects.requireNonNull(user);
            return user;
        } catch (NullPointerException e) {
            throw new SecurityException("Invalid session token.");
        } catch (RepositoryException e) {
            throw new SecurityException(e.getMessage());
        }
    }

}
