package com.ttbmp.cinehub.domain.utilities;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author Fabio Buracchi
 */
public class FactoryMap<T> {

    private final Map<Class<? extends T>, Supplier<? extends T>> map = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <S extends T> Supplier<S> get(Class<S> productClass) {
        return (Supplier<S>) map.get(productClass);
    }

    public <S extends T> void put(Class<S> productClass, Supplier<S> factory) {
        map.put(productClass, factory);
    }

}
