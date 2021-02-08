package com.ttbmp.cinehub.app.service.email;

/**
 * @author Palmieri Ivan
 */
public class EmailServiceRequest {
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

}
