package com.ttbmp.cinehub.core.utilities.result;

/**
 * @author Fabio Buracchi
 */
public final class Result<T> {

    private final T value;
    private final Throwable error;

    public Result(T value) {
        this.value = value;
        this.error = null;
    }

    public Result(Throwable error) {
        this.value = null;
        this.error = error;
    }

    public T getValue() {
        return value;
    }

    public Throwable getError() {
        return error;
    }

    public boolean hasError() {
        return error != null;
    }

}
