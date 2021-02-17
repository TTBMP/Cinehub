package com.ttbmp.cinehub.app.service.authentication;

/**
 * @author Fabio Buracchi
 */
public class MockAuthenticationService implements AuthenticationService {

    @Override
    public String signIn(String email, String password) throws AuthenticationException {
        return "";
    }

    @Override
    public String logIn(String email, String password) throws AuthenticationException {
        return "";
    }

    @Override
    public String authenticate(String sessionToken) throws AuthenticationException {
        return "1";
    }

}
