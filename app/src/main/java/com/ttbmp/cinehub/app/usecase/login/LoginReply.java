package com.ttbmp.cinehub.app.usecase.login;

public class LoginReply {

    String sessionCookie;

    public LoginReply(String sessionCookie) {
        this.sessionCookie = sessionCookie;
    }

    public String getSessionCookie() {
        return sessionCookie;
    }

    public void setSessionCookie(String sessionCookie) {
        this.sessionCookie = sessionCookie;
    }

}
