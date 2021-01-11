package com.ttbmp.cinehub.app.client.desktop.utilities;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author Fabio Buracchi
 */
public class FactoryMap {

    private final Map<Class<?>, Supplier<?>> map = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <T> Supplier<T> get(Class<T> productClass) {
        return (Supplier<T>) map.get(productClass);
    }

    public <T> void put(Class<T> productClass, Supplier<T> factory) {
        map.put(productClass, factory);
    }

}
