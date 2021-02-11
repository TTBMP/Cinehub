package com.ttbmp.cinehub.app.service.authentication;

import com.ttbmp.cinehub.service.authentication.FirebaseAuthenticationService;
import com.ttbmp.cinehub.service.authentication.FirebaseException;
import com.ttbmp.cinehub.service.authentication.FirebaseSession;

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
    public Session signIn(String email, String password) throws AuthenticationException {
        try {
            FirebaseSession session = authenticationService.createUser(email, password);
            return new Session(session.getUid(), session.getSessionCookie());
        } catch (FirebaseException e) {
            throw new AuthenticationException(e.getMessage());
        }
    }

    @Override
    public Session logIn(String email, String password) throws AuthenticationException {
        try {
            FirebaseSession session = authenticationService.authenticateUser(email, password);
            return new Session(session.getUid(), session.getSessionCookie());
        } catch (FirebaseException e) {
            throw new AuthenticationException(e.getMessage());
        }
    }

    @Override
    public Session verifySessionCookie(String sessionCookie) throws AuthenticationException {
        try {
            FirebaseSession session = authenticationService.verifySessionCookie(sessionCookie);
            return new Session(session.getUid(), session.getSessionCookie());
        } catch (FirebaseException e) {
            throw new AuthenticationException(e.getMessage());
        }
    }

}
