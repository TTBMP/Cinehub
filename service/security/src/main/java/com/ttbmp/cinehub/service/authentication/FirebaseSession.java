package com.ttbmp.cinehub.service.authentication;

import com.google.firebase.auth.UserRecord;

/**
 * @author Fabio Buracchi
 */
public class FirebaseSession {

    private String uid;
    private String email;
    private String sessionToken;

    public FirebaseSession(UserRecord loggedUserRecord) {
        this.uid = loggedUserRecord.getUid();
        this.email = loggedUserRecord.getEmail();
        this.sessionToken = loggedUserRecord.getUid();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

}
