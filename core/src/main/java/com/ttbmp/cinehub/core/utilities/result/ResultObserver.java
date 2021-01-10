package com.ttbmp.cinehub.core.utilities.result;

/**
 * @author Fabio Buracchi
 */
public interface ResultObserver<T> {

    void onSuccess(T t);

    void onError(Throwable error);

}
