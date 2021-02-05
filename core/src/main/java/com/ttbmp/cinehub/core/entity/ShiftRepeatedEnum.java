package com.ttbmp.cinehub.core.entity;

/**
 * @author Massimo Mazzetti
 */

public enum ShiftRepeatedEnum {
    EVERY_DAY("ogni giorno"),
    EVERY_WEEK("ogni settimana"),
    EVERY_MONTH("ogni mese");

    private final String value;

    ShiftRepeatedEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}


