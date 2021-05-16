package com.ttbmp.cinehub.app.service.security;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.user.UserRepository;
import com.ttbmp.cinehub.domain.User;
import com.ttbmp.cinehub.service.authentication.FirebaseAuthenticationService;
import com.ttbmp.cinehub.service.authentication.FirebaseException;

/**
 * @author Fabio Buracchi
 */
public class FirebaseAuthSecurityServiceAdapter implements SecurityService {

    private final UserRepository userRepository;

    public FirebaseAuthSecurityServiceAdapter(ServiceLocator serviceLocator) {
        this.userRepository = serviceLocator.getService(UserRepository.class);
    }

    @Override
    public String authenticate(String email, String password) throws SecurityException {
        try {
            var session = new FirebaseAuthenticationService().authenticateUser(email, password);
            return session.getSessionToken();
        } catch (FirebaseException e) {
            throw new SecurityException(e.getMessage());
        }
    }

    @Override
    public User authenticate(String sessionToken) throws SecurityException {
        try {
            var session = new FirebaseAuthenticationService().verifySessionToken(sessionToken);
            return userRepository.getUser(session.getUid());
        } catch (FirebaseException e) {
            try {
                return userRepository.getUser("");
            } catch (RepositoryException repositoryException) {
                throw new SecurityException(e.getMessage());
            }
        } catch (RepositoryException e) {
            throw new SecurityException(e.getMessage());
        }
    }

}
