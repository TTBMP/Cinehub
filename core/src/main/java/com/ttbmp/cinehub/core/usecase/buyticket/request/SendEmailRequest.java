package com.ttbmp.cinehub.core.usecase.buyticket.request;

import com.ttbmp.cinehub.core.usecase.Request;
/**
 * @author Palmieri Ivan
 */
public class SendEmailRequest extends Request {


    public static final Request.Error MISSING_RECIPIENT_ERROR = new Request.Error("Recipient can't be null");

    private Integer userId;

    public SendEmailRequest(Integer userId) {
        this.userId = userId;
    }

    @Override
    public void onValidate() {
        if (userId == null) {
            addError(MISSING_RECIPIENT_ERROR);
        }
    }

    public int getId() {
        return userId;
    }

    public void setId(int id) {
        this.userId = id;
    }
}
