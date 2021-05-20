package com.ttbmp.cinehub.app.service.security;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.user.UserRepository;
import com.ttbmp.cinehub.domain.User;

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
        switch (email) {
            case "customer":
                return "CUSTOMER";
            case "projectionist":
                return "PROJECTIONIST";
            case "usher":
                return "USHER";
            case "manager":
                return "MANAGER";
            default:
                throw new SecurityException("Wrong username or password.");
        }
    }

    @Override
    public User authenticate(String sessionToken) throws SecurityException {
        try {
            switch (sessionToken) {
                case "CUSTOMER":
                    return userRepository.getUser("-2");
                case "MANAGER":
                    return userRepository.getUser("-1");
                case "PROJECTIONIST":
                    return userRepository.getUser("1");
                case "USHER":
                    return userRepository.getUser("2");
                default:
                    throw new SecurityException("Invalid session token");
            }
        } catch (NullPointerException e) {
            throw new SecurityException("Session token is null");
        } catch (RepositoryException e) {
            throw new SecurityException(e.getMessage());
        }
    }

}
