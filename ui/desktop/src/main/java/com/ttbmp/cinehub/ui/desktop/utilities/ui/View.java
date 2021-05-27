package com.ttbmp.cinehub.ui.desktop.utilities.ui;

import javafx.scene.Parent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public abstract class View {

    private final List<String> stylesheetList = new ArrayList<>();

    public abstract void load() throws IOException;

    public abstract Parent getRoot();

    public abstract ViewController getController();

    public List<String> getStylesheetList() {
        return stylesheetList;
    }

    public void addStylesheet(String stylesheet) {
        stylesheetList.add(Objects.requireNonNull(this.getClass().getResource("/styles/" + stylesheet)).toExternalForm());
    }

    public void addStylesheet(Collection<? extends String> stylesheetCollection) {
        stylesheetList.addAll(stylesheetCollection.stream()
                .map(stylesheet -> Objects.requireNonNull(this.getClass().getResource("/styles/" + stylesheet)).toExternalForm())
                .collect(Collectors.toList())
        );
    }

}
