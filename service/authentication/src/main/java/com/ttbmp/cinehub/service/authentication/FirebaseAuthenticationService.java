package com.ttbmp.cinehub.service.authentication;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author Fabio Buracchi, Ivan Palmieri
 */
public class FirebaseAuthenticationService {

    private final FirebaseAuth firebaseAuth;

    public FirebaseAuthenticationService() throws FirebaseException {
        try {
            FileInputStream settings = new FileInputStream(
                    this.getClass().getResource("resources/firebase_settings.json").getPath()
            );
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
            String idToken = firebaseAuth.createCustomToken(userRecord.getUid());
            String sessionCookie = createSessionCookie(idToken);
            return new FirebaseSession(userRecord, sessionCookie);
        } catch (FirebaseAuthException e) {
            throw new FirebaseException(e.getMessage());
        }
    }

    @SuppressWarnings("unused")
    public FirebaseSession authenticateUser(String email, String password) throws FirebaseException {
        try {
            UserRecord userRecord = firebaseAuth.getUserByEmail(email);
            String idToken = firebaseAuth.createCustomToken(userRecord.getUid());
            String sessionCookie = createSessionCookie(idToken);
            return new FirebaseSession(userRecord, sessionCookie);
        } catch (FirebaseAuthException e) {
            throw new FirebaseException(e.getMessage());
        }
    }

    public FirebaseSession verifySessionCookie(String sessionCookie) throws FirebaseException {
        try {
            FirebaseToken firebaseToken = firebaseAuth.verifySessionCookie(sessionCookie, true);
            return new FirebaseSession(
                    firebaseAuth.getUser(firebaseToken.getUid()),
                    sessionCookie
            );
        } catch (FirebaseAuthException e) {
            throw new FirebaseException(e.getMessage());
        }
    }

    private String createSessionCookie(String idToken) throws FirebaseException {
        try {
            SessionCookieOptions options = SessionCookieOptions.builder()
                    .setExpiresIn(TimeUnit.DAYS.toMillis(5))
                    .build();
            return firebaseAuth.createSessionCookie(idToken, options);
        } catch (FirebaseAuthException e) {
            throw new FirebaseException(e.getMessage());
        }
    }

}
