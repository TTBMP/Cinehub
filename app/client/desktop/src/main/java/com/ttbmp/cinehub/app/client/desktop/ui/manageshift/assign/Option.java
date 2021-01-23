package com.ttbmp.cinehub.app.client.desktop.ui.manageshift.assign;

/**
 * @author Massimo Mazzetti
 */

public enum Option {
    EVERY_DAY("ogni giorno"),
    EVERY_WEEK("ogni settimana"),
    EVERY_MONTH("ogni mese");

    private final String value;

    Option(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}


