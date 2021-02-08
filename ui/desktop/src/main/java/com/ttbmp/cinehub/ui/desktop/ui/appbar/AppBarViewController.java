package com.ttbmp.cinehub.ui.desktop.ui.appbar;

import com.ttbmp.cinehub.ui.desktop.ui.buyticket.BuyTicketActivity;
import com.ttbmp.cinehub.ui.desktop.ui.manageshift.ManageShiftActivity;
import com.ttbmp.cinehub.ui.desktop.ui.viewpersonalschedule.ViewPersonalScheduleActivity;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavActivityDestination;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Fabio Buracchi
 */
public class AppBarViewController extends ViewController {

    private final Map<Class<? extends Activity>, Tab> activityTabMap = new HashMap<>();

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab buyTicketTab;

    @FXML
    private Tab viewPersonalScheduleTab;

    @FXML
    private Tab manageShiftTab;

    @Override
    protected void onLoad() {
        loadActivityTabMap();
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

    private void loadActivityTabMap() {
        activityTabMap.put(BuyTicketActivity.class, buyTicketTab);
        activityTabMap.put(ManageShiftActivity.class, manageShiftTab);
        activityTabMap.put(ViewPersonalScheduleActivity.class, viewPersonalScheduleTab);
    }

}