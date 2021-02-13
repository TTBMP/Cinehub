package com.ttbmp.cinehub.app.service.authentication;

public class Session {

    private String userId;
    private String sessionCookie;

    public Session(String userId, String sessionCookie) {
        this.userId = userId;
        this.sessionCookie = sessionCookie;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSessionCookie() {
        return sessionCookie;
    }

    public void setSessionCookie(String sessionCookie) {
        this.sessionCookie = sessionCookie;
    }

}
