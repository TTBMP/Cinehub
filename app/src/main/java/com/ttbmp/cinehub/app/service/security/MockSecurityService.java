package com.ttbmp.cinehub.app.service.security;

/**
 * @author Fabio Buracchi
 */
public class MockSecurityService implements SecurityService {

    @Override
    public String addAuthenticatedUser(String email, String password) {
        return "";
    }

    @Override
    public String authenticate(String email, String password) throws SecurityException {
        return "";
    }

    @Override
    public User authenticate(String sessionToken) throws SecurityException {
        return new User("1", new Role[]{Role.EMPLOYEE_ROLE, Role.PROJECTIONIST_ROLE});
    }

}
