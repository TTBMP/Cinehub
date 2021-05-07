package com.ttbmp.cinehub.app.service.security;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.user.UserRepository;

/**
 * @author Fabio Buracchi
 */
public class MockSecurityService implements SecurityService {

    private final UserRepository userRepository;

    public MockSecurityService(ServiceLocator serviceLocator) {
        this.userRepository = serviceLocator.getService(UserRepository.class);
    }

    @Override
    public String addAuthenticatedUser(String email, String password) {
        return "";
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
        if (sessionToken == null) {
            sessionToken = "";
        }
        switch (sessionToken) {
            case "CUSTOMER":
                return userRepository.getUser("4");
            case "PROJECTIONIST":
                return userRepository.getUser("1");
            case "USHER":
                return userRepository.getUser("2");
            case "MANAGER":
                return userRepository.getUser("5");
            default:
                return userRepository.getUser("");
        }
    }

}
