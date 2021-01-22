package com.ttbmp.cinehub.core.utilities.notification;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Fabio Buracchi
 */
public class Notification {

    private final List<Notification.Error> errorList = new ArrayList<>();

    public boolean hasError() {
        return !errorList.isEmpty();
    }

    public void addError(Notification.Error error) {
        errorList.add(error);
    }

    public List<Notification.Error> getErrorList() {
        return errorList;
    }

    public static class Error {

        private final String message;

        public Error(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

    }
}
