package com.ttbmp.cinehub.core.utilities.result;

import java.util.Objects;

/**
 * @author Fabio Buracchi
 */
public class Result<T> {

    private T value = null;
    private Throwable error = null;

    public Result(T result) {
        this.value = result;
    }

    public Result(Throwable error) {
        this.error = error;
    }

    public void addObserver(ResultObserver<? super T> observer) {
        Objects.requireNonNull(observer);
        if (error != null || value == null) {
            observer.onError(error);
        } else {
            observer.onSuccess(value);
        }
    }

}
