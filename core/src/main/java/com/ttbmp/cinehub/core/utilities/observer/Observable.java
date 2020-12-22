package com.ttbmp.cinehub.core.utilities.observer;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Fabio Buracchi
 */
public class Observable<T> {

    private final Collection<Observer<? super T>> observerList = new ArrayList<>();

    private T value;

    public Observable() {
        value = null;
    }

    public Observable(T value) {
        this.value = value;
    }

    public void addObserver(Observer<? super T> observer) {
        observerList.add(observer);
    }

    public void removeObserver(Observer<? super T> observer) {
        observerList.remove(observer);
    }

    protected void notifyObservers(T state) {
        for (Observer<? super T> observer : observerList) {
            observer.onChanged(state);
        }
    }

    public void setValue(T value) {
        this.value = value;
        notifyObservers(value);
    }

    public T getValue() {
        return value;
    }

}
