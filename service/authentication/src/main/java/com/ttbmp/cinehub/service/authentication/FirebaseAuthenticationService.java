package com.ttbmp.cinehub.service.authentication;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.*;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Fabio Buracchi, Ivan Palmieri
 */
public class FirebaseAuthenticationService {

    private final FirebaseAuth firebaseAuth;

    public FirebaseAuthenticationService() throws FirebaseException {
        try {
            InputStream settings = this.getClass().getResourceAsStream("/firebase_settings.json");
            FirebaseOptions options = FirebaseOptions.builder()
                    .setDatabaseUrl("https://noreply@cinehub-d2abc.firebaseio.com/")
                    .setCredentials(GoogleCredentials.fromStream(settings))
                    .build();
            FirebaseApp.initializeApp(options);
            firebaseAuth = FirebaseAuth.getInstance();
        } catch (IOException e) {
            throw new FirebaseException(e.getMessage());
        }
    }

    public FirebaseSession createUser(String email, String password) throws FirebaseException {
        try {
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(email)
                    .setEmailVerified(false)
                    .setPassword(password)
                    .setDisabled(false);
            UserRecord userRecord = firebaseAuth.createUser(request);
            return new FirebaseSession(userRecord);
        } catch (FirebaseAuthException e) {
            throw new FirebaseException(e.getMessage());
        }
    }

    @SuppressWarnings("unused")
    public FirebaseSession authenticateUser(String email, String password) throws FirebaseException {
        try {
            UserRecord userRecord = firebaseAuth.getUserByEmail(email);
            return new FirebaseSession(userRecord);
        } catch (FirebaseAuthException e) {
            throw new FirebaseException(e.getMessage());
        }
    }

    public FirebaseSession verifySessionCookie(String sessionCookie) throws FirebaseException {
        try {
            String email = sessionCookie;
            UserRecord userRecord = firebaseAuth.getUserByEmail(email);
            return new FirebaseSession(userRecord);
        } catch (FirebaseAuthException e) {
            throw new FirebaseException(e.getMessage());
        }
    }

}