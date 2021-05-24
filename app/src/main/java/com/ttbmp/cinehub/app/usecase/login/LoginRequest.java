package com.ttbmp.cinehub.app.usecase.login;

import com.ttbmp.cinehub.app.utilities.request.Request;

public class LoginRequest extends Request {

    public static final Request.Error MISSING_USERNAME_ERROR = new Request.Error("Username can't be null");
    public static final Request.Error MISSING_PASSWORD_ERROR = new Request.Error("Password can't be null");

    private String username;
    private String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    protected void onValidate() {
        if (username == null) {
            addError(MISSING_USERNAME_ERROR);
        }
        if (password == null) {
            addError(MISSING_PASSWORD_ERROR);
        }

    }

}
