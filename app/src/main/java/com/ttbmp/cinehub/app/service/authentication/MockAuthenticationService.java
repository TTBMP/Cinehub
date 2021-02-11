package com.ttbmp.cinehub.app.service.authentication;

/**
 * @author Fabio Buracchi
 */
public class MockAuthenticationService implements AuthenticationService {

    @Override
    public Session signIn(String email, String password) throws AuthenticationException {
        return new Session("1", "");
    }

    @Override
    public Session logIn(String email, String password) throws AuthenticationException {
        return new Session("1", "");
    }

    @Override
    public Session verifySessionCookie(String sessionCookie) throws AuthenticationException {
        return new Session("1", "");
    }

    @Override
    public String sigIn(String email, String password) {
        return "keyId";
    }

}
