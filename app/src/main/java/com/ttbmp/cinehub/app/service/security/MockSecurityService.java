package com.ttbmp.cinehub.app.service.security;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.user.MockUserRepository;
import com.ttbmp.cinehub.app.repository.user.UserRepository;
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
        return MockUserRepository.getUserDataList().stream()
                .filter(userData -> userData.getEmail().equals(email))
                .map(MockUserRepository.UserData::getId)
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
