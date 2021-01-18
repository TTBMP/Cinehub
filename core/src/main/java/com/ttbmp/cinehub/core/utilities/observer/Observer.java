package com.ttbmp.cinehub.core.utilities.observer;

/**
 * @author Fabio Buracchi
 */
public interface Observer<T> {

    void onChanged(T t);

}
