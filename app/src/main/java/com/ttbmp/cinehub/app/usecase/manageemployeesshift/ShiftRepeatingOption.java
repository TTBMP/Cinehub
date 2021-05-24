package com.ttbmp.cinehub.app.usecase.manageemployeesshift;

/**
 * @author Massimo Mazzetti
 */
public enum ShiftRepeatingOption {

    EVERY_DAY("Every day"),
    EVERY_WEEK("Every week"),
    EVERY_MONTH("Every month");

    private final String value;

    ShiftRepeatingOption(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}


