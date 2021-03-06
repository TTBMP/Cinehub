package com.ttbmp.cinehub.app.service.email;

import com.ttbmp.cinehub.app.utilities.request.Request;

/**
 * @author Ivan Palmieri
 */
public class EmailServiceRequest extends Request {

    public static final Request.Error EMAIL_ERROR = new Request.Error("The email is invalid");

    private String email;
    private String object;

    public EmailServiceRequest(String email, String object) {
        this.email = email;
        this.object = object;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    @Override
    protected void onValidate() {
        if (!email.contains("@")) {
            addError(EMAIL_ERROR);
        }
    }

}
