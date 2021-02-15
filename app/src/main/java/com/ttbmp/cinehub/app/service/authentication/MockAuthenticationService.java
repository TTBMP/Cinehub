package com.ttbmp.cinehub.app.service.authentication;

/**
 * @author Fabio Buracchi
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
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

}
