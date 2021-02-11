package com.ttbmp.cinehub.app.service.authentication;

/**
 * @author Fabio Buracchi
 */
public class MockAuthenticationService implements AuthenticationService {

    @Override
    public Integer sigInOld() {
        return 1;
    }

    @Override
    public void signIn(String email, String password) throws AuthenticationException {

    }

    @Override
    public void logIn(String email, String password) throws AuthenticationException {

    }

    @Override
    public void verifySessionCookie(String sessionCookie) throws AuthenticationException {

    }

}
