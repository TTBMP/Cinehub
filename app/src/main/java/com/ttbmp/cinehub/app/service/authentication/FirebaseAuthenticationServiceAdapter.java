package com.ttbmp.cinehub.app.service.authentication;

import com.ttbmp.cinehub.service.authentication.FirebaseAuthenticationService;
import com.ttbmp.cinehub.service.authentication.FirebaseException;

/**
 * @author Fabio Buracchi
 */
public class FirebaseAuthenticationServiceAdapter implements AuthenticationService {

    FirebaseAuthenticationService authenticationService;

    public FirebaseAuthenticationServiceAdapter() throws AuthenticationException {
        try {
            authenticationService = new FirebaseAuthenticationService();
        } catch (FirebaseException e) {
            throw new AuthenticationException(e.getMessage());
        }
    }

    @Override
    public Integer sigInOld() {
        return null;
    }

    @Override
    public void signIn(String email, String password) throws AuthenticationException {
        try {
            authenticationService.createUser(email, password);
        } catch (FirebaseException e) {
            throw new AuthenticationException(e.getMessage());
        }
    }

    @Override
    public void logIn(String email, String password) throws AuthenticationException {
        try {
            authenticationService.authenticateUser(email, password);
        } catch (FirebaseException e) {
            throw new AuthenticationException(e.getMessage());
        }
    }

    @Override
    public void verifySessionCookie(String sessionCookie) throws AuthenticationException {
        try {
            authenticationService.verifySessionCookie(sessionCookie);
        } catch (FirebaseException e) {
            throw new AuthenticationException(e.getMessage());
        }
    }

}
