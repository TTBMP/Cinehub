package com.ttbmp.cinehub.app.utilities;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Fabio Buracchi
 */
public class InstanceMap {

    private final Map<Class<?>, Object> map = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <T> T get(Class<T> objectClass) {
        return (T) map.get(objectClass);
    }

    public <T> void put(Class<T> objectClass, T instance) {
        map.put(objectClass, instance);
    }

}
