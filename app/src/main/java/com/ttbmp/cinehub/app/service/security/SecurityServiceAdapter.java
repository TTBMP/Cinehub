package com.ttbmp.cinehub.app.service.security;

import com.ttbmp.cinehub.app.repository.user.UserRepository;
import com.ttbmp.cinehub.service.authentication.FirebaseAuthenticationService;
import com.ttbmp.cinehub.service.authentication.FirebaseException;
import com.ttbmp.cinehub.service.authentication.FirebaseSession;

/**
 * @author Fabio Buracchi
 */
public class SecurityServiceAdapter implements SecurityService {

    private final FirebaseAuthenticationService authenticationService;
    private final UserRepository userRepository;

    public SecurityServiceAdapter(UserRepository userRepository) throws SecurityException {
        try {
            this.authenticationService = new FirebaseAuthenticationService();
            this.userRepository = userRepository;
        } catch (FirebaseException e) {
            throw new SecurityException(e.getMessage());
        }
    }

    @Override
    public String addAuthenticatedUser(String email, String password) throws SecurityException {
        try {
            FirebaseSession session = authenticationService.createUser(email, password);
            return session.getSessionCookie();
        } catch (FirebaseException e) {
            throw new SecurityException(e.getMessage());
        }
    }

    @Override
    public String authenticate(String email, String password) throws SecurityException {
        try {
            FirebaseSession session = authenticationService.authenticateUser(email, password);
            return session.getSessionCookie();
        } catch (FirebaseException e) {
            throw new SecurityException(e.getMessage());
        }
    }

    @Override
    public User authenticate(String sessionToken) throws SecurityException {
        try {
            User user;
            FirebaseSession session = authenticationService.verifySessionCookie(sessionToken);
            user = new User(userRepository.getUser(session.getUid()).getId(), null);    //TODO
            return user;
        } catch (FirebaseException e) {
            throw new SecurityException(e.getMessage());
        }
    }

}
