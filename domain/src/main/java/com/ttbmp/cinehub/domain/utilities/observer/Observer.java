package com.ttbmp.cinehub.domain.utilities.observer;

/**
 * @author Fabio Buracchi
 */
public interface Observer<T> {

    void onChanged(T t);

}
