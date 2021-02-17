package com.ttbmp.cinehub.app.usecase.login;

public class LoginResponse {

    String sessionCookie;

    public LoginResponse(String sessionCookie) {
        this.sessionCookie = sessionCookie;
    }

    public String getSessionCookie() {
        return sessionCookie;
    }

    public void setSessionCookie(String sessionCookie) {
        this.sessionCookie = sessionCookie;
    }

}
