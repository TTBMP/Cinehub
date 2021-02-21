package com.ttbmp.cinehub.app.service.security;

/**
 * @author Fabio Buracchi
 */
public interface SecurityService {

    String addAuthenticatedUser(String email, String password) throws SecurityException;

    String authenticate(String email, String password) throws SecurityException;

    User authenticate(String sessionToken) throws SecurityException;

}
