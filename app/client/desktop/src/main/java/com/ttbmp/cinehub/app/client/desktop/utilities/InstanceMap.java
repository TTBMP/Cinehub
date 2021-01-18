package com.ttbmp.cinehub.app.client.desktop.utilities;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Fabio Buracchi
 */
public class InstanceMap<T> {

    private final Map<Class<? extends T>, T> map = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <S extends T> S get(Class<S> objectClass) {
        return (S) map.get(objectClass);
    }

    public <S extends T> void put(Class<S> objectClass, S instance) {
        map.put(objectClass, instance);
    }

}
