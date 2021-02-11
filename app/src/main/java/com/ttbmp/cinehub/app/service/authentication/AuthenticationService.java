package com.ttbmp.cinehub.app.service.authentication;

/**
 * @author Fabio Buracchi
 */
public interface AuthenticationService {

    Session signIn(String email, String password) throws AuthenticationException;

    Session logIn(String email, String password) throws AuthenticationException;

    Session verifySessionCookie(String sessionCookie) throws AuthenticationException;

    String sigIn(String email, String password);

}
