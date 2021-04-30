package com.ttbmp.cinehub.app.repository.shift;

/**
 * @author Massimo Mazzetti
 */
public class ShiftSaveException extends Exception {

    public static final String ALREADY_EXIST_ERROR = "Shift Already Exist";
    public static final String NOT_EXIST_ERROR = "Shift Not Exist";

    public ShiftSaveException(String message) {
        super(message);
    }

}
