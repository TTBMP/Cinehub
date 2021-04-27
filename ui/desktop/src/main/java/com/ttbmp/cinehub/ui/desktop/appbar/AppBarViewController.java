package com.ttbmp.cinehub.ui.desktop.appbar;

import com.ttbmp.cinehub.ui.desktop.about.AboutActivity;
import com.ttbmp.cinehub.ui.desktop.buyticket.BuyTicketActivity;
import com.ttbmp.cinehub.ui.desktop.manageshift.ManageShiftActivity;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavActivityDestination;
import com.ttbmp.cinehub.ui.desktop.viewpersonalschedule.ViewPersonalScheduleActivity;
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
    private Tab aboutTab;

    @FXML
    private Tab viewPersonalScheduleTab;

    @FXML
    private Tab manageShiftTab;

    @Override
    protected void onLoad() {
        loadActivityTabMap();
        for (var entry : activityTabMap.entrySet()) {
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
        activityTabMap.put(AboutActivity.class, aboutTab);
    }

}
