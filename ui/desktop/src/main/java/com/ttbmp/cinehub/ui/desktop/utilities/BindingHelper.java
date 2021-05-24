package com.ttbmp.cinehub.ui.desktop.utilities;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class BindingHelper {

    private BindingHelper() {
    }

    public static <T> void bind(ObservableList<T> list, ObservableList<T> other) {
        other.clear();
        other.addAll(list);
        other.addListener((ListChangeListener.Change<? extends T> c) -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    list.addAll(c.getAddedSubList());
                }
                if (c.wasRemoved()) {
                    list.removeAll(c.getRemoved());
                }
            }
        });
    }

}
