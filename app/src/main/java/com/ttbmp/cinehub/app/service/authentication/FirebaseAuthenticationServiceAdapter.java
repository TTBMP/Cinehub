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
    public String signIn(String email, String password) throws AuthenticationException {
        try {
            var session = authenticationService.createUser(email, password);
            return session.getSessionCookie();
        } catch (FirebaseException e) {
            throw new AuthenticationException(e.getMessage());
        }
    }

    @Override
    public String logIn(String email, String password) throws AuthenticationException {
        try {
            var session = authenticationService.authenticateUser(email, password);
            return session.getSessionCookie();
        } catch (FirebaseException e) {
            throw new AuthenticationException(e.getMessage());
        }
    }

    @Override
    public String authenticate(String sessionToken) throws AuthenticationException {
        try {
            var session = authenticationService.verifySessionCookie(sessionToken);
            return session.getUid();
        } catch (FirebaseException e) {
            throw new AuthenticationException(e.getMessage());
        }
    }

}
