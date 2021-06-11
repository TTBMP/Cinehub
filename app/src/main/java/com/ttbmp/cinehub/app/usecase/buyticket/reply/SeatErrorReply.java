package com.ttbmp.cinehub.app.usecase.buyticket.reply;

public class SeatErrorReply {

    private String error;

    public SeatErrorReply(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
