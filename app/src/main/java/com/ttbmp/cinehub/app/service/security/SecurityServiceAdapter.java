package com.ttbmp.cinehub.app.service.security;

import com.ttbmp.cinehub.app.repository.user.UserRepository;

/**
 * @author Fabio Buracchi
 */
public class SecurityServiceAdapter implements SecurityService {

    private final UserRepository userRepository;

    public SecurityServiceAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String addAuthenticatedUser(String email, String password) throws SecurityException {
//        try {
//            FirebaseSession session = new FirebaseAuthenticationService().createUser(email, password);
//            return session.getSessionCookie();
//        } catch (FirebaseException e) {
//            throw new SecurityException(e.getMessage());
//        }
        return "";
    }

    @Override
    public String authenticate(String email, String password) throws SecurityException {
//        try {
//            FirebaseSession session = new FirebaseAuthenticationService().authenticateUser(email, password);
//            return session.getSessionCookie();
//        } catch (FirebaseException e) {
//            throw new SecurityException(e.getMessage());
//        }
        return "";
    }

    @Override
    public User authenticate(String sessionToken) throws SecurityException {
//        try {
//            FirebaseSession session = new FirebaseAuthenticationService().verifySessionCookie(sessionToken);
//            return userRepository.getUser(session.getUid());
//        } catch (FirebaseException e) {
//            throw new SecurityException(e.getMessage());
//        }
        return userRepository.getUser("1");
    }

}
