package com.ttbmp.cinehub.core.usecase;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Fabio Buracchi
 */
public abstract class Request {

    private final List<Request.Error> errorList = new ArrayList<>();

    public static void validate(Request request) throws NullRequestException, InvalidRequestException {
        if (request == null) {
            throw new NullRequestException();
        }
        request.onValidate();
        if (!request.getErrorList().isEmpty()) {
            throw new InvalidRequestException();
        }
    }

    public List<Request.Error> getErrorList() {
        return errorList;
    }

    protected void addError(Request.Error error) {
        errorList.add(error);
    }

    protected abstract void onValidate();

    public static class Error {

        private final String message;

        public Error(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

    }

    public static class InvalidRequestException extends Exception {

    }

    public static class NullRequestException extends Exception {
    }

}
