package com.ttbmp.cinehub.app.client.desktop.ui.appbar;

import com.ttbmp.cinehub.app.client.desktop.ui.viewpersonalschedule.ViewPersonalScheduleActivity;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.Controller;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.navigation.NavActivityDestination;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.navigation.NavController;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class AppBarController extends Controller {

    private final Map<Class<? extends Activity>, Tab> activityTabMap = new HashMap<>();
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tab1;

    @Override
    public void onLoad() {
        activityTabMap.put(ViewPersonalScheduleActivity.class, tab1);
        for (Map.Entry<Class<? extends Activity>, Tab> entry : activityTabMap.entrySet()) {
            if (activity.getClass().equals(entry.getKey())) {
                tabPane.getSelectionModel().select(entry.getValue());
            }
            activityTabMap.get(entry.getKey()).setOnSelectionChanged(e -> {
                if (entry.getValue().isSelected()) {
                    try {
                        navController.navigate(new NavActivityDestination(entry.getKey().getConstructor().newInstance()));
                    } catch (IOException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });
        }
    }

    public void initialize(Activity activity, NavController navController) {
        this.activity = activity;
        this.navController = navController;
        onLoad();
    }

}
