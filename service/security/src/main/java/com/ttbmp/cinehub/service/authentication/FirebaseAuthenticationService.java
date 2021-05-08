package com.ttbmp.cinehub.service.authentication;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Fabio Buracchi, Ivan Palmieri
 */
public class FirebaseAuthenticationService {

    private static FirebaseAuth firebaseAuth;

    public FirebaseAuthenticationService() throws FirebaseException {
        try {
            init();
        } catch (IOException e) {
            throw new FirebaseException(e.getMessage());
        }
    }

    private synchronized void init() throws IOException {
        if (firebaseAuth == null) {
            var settings = FirebaseAuthenticationService.class.getResourceAsStream("/firebase_settings.json");
            var options = FirebaseOptions.builder()
                    .setDatabaseUrl("https://noreply@cinehub-d2abc.firebaseio.com/")
                    .setCredentials(GoogleCredentials.fromStream(Objects.requireNonNull(settings)))
                    .build();
            FirebaseApp.initializeApp(options);
            firebaseAuth = FirebaseAuth.getInstance();
        }
    }

    @SuppressWarnings("unused")
    public FirebaseSession authenticateUser(String email, String password) throws FirebaseException {
        try {
            var userRecord = firebaseAuth.getUserByEmail(email);
            return new FirebaseSession(userRecord);
        } catch (FirebaseAuthException e) {
            throw new FirebaseException(e.getMessage());
        }
    }

    public FirebaseSession verifySessionToken(String sessionToken) throws FirebaseException {
        try {
            var email = sessionToken;
            var userRecord = firebaseAuth.getUserByEmail(email);
            return new FirebaseSession(userRecord);
        } catch (FirebaseAuthException e) {
            throw new FirebaseException(e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new FirebaseException("Invalid session token");
        }
    }

}
