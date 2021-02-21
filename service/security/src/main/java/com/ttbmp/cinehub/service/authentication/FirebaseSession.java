package com.ttbmp.cinehub.service.authentication;

import com.google.firebase.auth.UserRecord;

/**
 * @author Fabio Buracchi
 */
public class FirebaseSession {

    private String uid;
    private String email;
    private String sessionCookie;

    public FirebaseSession(UserRecord loggedUserRecord) {
        this.uid = loggedUserRecord.getUid();
        this.email = loggedUserRecord.getEmail();
        this.sessionCookie = loggedUserRecord.getEmail();
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

    public String getSessionCookie() {
        return sessionCookie;
    }

    public void setSessionCookie(String sessionCookie) {
        this.sessionCookie = sessionCookie;
    }

}
