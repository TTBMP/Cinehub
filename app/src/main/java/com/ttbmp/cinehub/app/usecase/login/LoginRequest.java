package com.ttbmp.cinehub.app.usecase.login;

import com.ttbmp.cinehub.app.utilities.request.Request;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class LoginRequest extends Request {

    public static final Request.Error MISSING_USERNAME_ERROR = new Request.Error("Username can't be null");
    public static final Request.Error MISSING_PASSWORD_ERROR = new Request.Error("Password can't be null");

    String username;
    String password;

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
