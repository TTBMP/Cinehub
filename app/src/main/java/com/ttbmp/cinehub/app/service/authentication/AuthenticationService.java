package com.ttbmp.cinehub.app.service.authentication;

/**
 * @author Fabio Buracchi
 */
public interface AuthenticationService {

    /**
     *
     * @param email
     * @param password
     * @return the session token on success
     * @throws AuthenticationException
     */
    String signIn(String email, String password) throws AuthenticationException;

    /**
     *
     * @param email
     * @param password
     * @return the session token on success
     * @throws AuthenticationException
     */
    String logIn(String email, String password) throws AuthenticationException;

    /**
     *
     * @param sessionToken
     * @return the user id on success
     * @throws AuthenticationException
     */
    String authenticate(String sessionToken) throws AuthenticationException;

}
