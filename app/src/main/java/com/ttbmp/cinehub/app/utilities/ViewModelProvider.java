package com.ttbmp.cinehub.app.utilities;

import javax.validation.constraints.NotNull;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Fabio Buracchi
 */
public class ViewModelProvider {

    private static final Map<Class<? extends ViewModel>, Object> VIEW_MODEL_INSTANCE_MAP = new HashMap<>();

    private ViewModelProvider() {
    }

    public static <T extends ViewModel> T getViewModel(@NotNull Class<T> viewModelClass) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        VIEW_MODEL_INSTANCE_MAP.putIfAbsent(viewModelClass, viewModelClass.getDeclaredConstructor().newInstance());
        return viewModelClass.cast(VIEW_MODEL_INSTANCE_MAP.get(viewModelClass));
    }

}
