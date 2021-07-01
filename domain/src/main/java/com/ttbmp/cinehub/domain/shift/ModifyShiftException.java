package com.ttbmp.cinehub.domain.shift;

public class ModifyShiftException extends Exception {

    public static final String ALREADY_EXIST_ERROR = "Shift Already Exist";
    public static final String INVALID_DATE = "Invalid Date of Shift";


    public ModifyShiftException(String message) {
        super(message);
    }

}
