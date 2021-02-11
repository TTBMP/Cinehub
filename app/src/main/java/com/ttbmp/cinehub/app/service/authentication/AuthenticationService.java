package com.ttbmp.cinehub.app.service.authentication;

/**
 * @author Fabio Buracchi
 */
public interface AuthenticationService {

    Integer sigInOld();

    void signIn(String email, String password) throws AuthenticationException;

    void logIn(String email, String password) throws AuthenticationException;

    void verifySessionCookie(String sessionCookie) throws AuthenticationException;

}
