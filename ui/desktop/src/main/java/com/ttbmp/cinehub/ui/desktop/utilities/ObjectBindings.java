package com.ttbmp.cinehub.ui.desktop.utilities;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.value.ObservableValue;

import java.util.function.Function;

public class ObjectBindings {

    private ObjectBindings() {

    }

    public static <S, R> ObjectBinding<R> map(ObservableValue<S> source, Function<? super S, ? extends R> function) {
        return map(source, function, null);
    }

    public static <S, R> ObjectBinding<R> map(ObservableValue<S> source, Function<? super S, ? extends R> function, R defaultValue) {
        return Bindings.createObjectBinding(() -> {
                    var sourceValue = source.getValue();
                    return sourceValue == null ? defaultValue : function.apply(sourceValue);
                },
                source);
    }

    public static <T, S extends T> ObjectBinding<T> cast(ObservableValue<S> source) {
        return Bindings.createObjectBinding(source::getValue, source);
    }

}