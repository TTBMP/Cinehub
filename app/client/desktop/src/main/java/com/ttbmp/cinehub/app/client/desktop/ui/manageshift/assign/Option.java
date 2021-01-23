package com.ttbmp.cinehub.app.client.desktop.ui.manageshift.assign;



public enum Option {
    EVERY_DAY("ogni giorno"),
    EVERY_WEEK("ogni settimana"),
    EVERY_MONTH("ogni mese");

    private final String option;

    Option(String option) {
        System.out.println(option);
        this.option = option;
    }

    public String Option() {
        return option;
    }
}


