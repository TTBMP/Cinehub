package com.ttbmp.cinehub.domain.shift.factory;

public class CreateShiftException extends Exception{
    public static final String ALREADY_EXIST_ERROR = "Shift Already Exist";
    public static final String NOT_EXIST_ERROR = "Shift Not Exist";

    public CreateShiftException(String message) {
        super(message);
    }
}
