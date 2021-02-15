package com.ttbmp.cinehub.domain.shift;

public class ModifyShiftException extends Exception {
    public static final String ALREADY_EXIST_ERROR = "Shift Already Exist";
    public static final String NOT_EXIST_ERROR = "Shift Not Exist";

    public ModifyShiftException(String message) {
        super(message);
    }
}
