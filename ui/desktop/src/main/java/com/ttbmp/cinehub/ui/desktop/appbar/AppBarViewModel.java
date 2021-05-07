package com.ttbmp.cinehub.ui.desktop.appbar;

import com.ttbmp.cinehub.ui.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;

import java.util.HashMap;
import java.util.Map;

public class AppBarViewModel implements ViewModel {

    private final Map<Class<? extends Activity>, Tab> activityTabMap = new HashMap<>();

    private final ObservableList<Tab> tabList = FXCollections.observableArrayList();

    public Map<Class<? extends Activity>, Tab> getActivityTabMap() {
        return activityTabMap;
    }

    public ObservableList<Tab> getTabList() {
        return tabList;
    }

}
