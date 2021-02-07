package com.ttbmp.cinehub.app.client.desktop.ui.manageshift.table;

import com.ttbmp.cinehub.app.client.desktop.ui.manageshift.ManageEmployeesShiftViewModel;
import com.ttbmp.cinehub.app.client.desktop.ui.manageshift.assign.AssignShiftView;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.navigation.NavController;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.navigation.NavDestination;
import com.ttbmp.cinehub.core.dto.ShiftDto;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author Massimo Mazzetti
 */


public class CalendarShiftItemViewController extends ViewController {

    DayWeek dayWeek;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private VBox shiftVBox;
    @FXML
    private Button addButton;

    public void load(Activity activity, NavController navController, DayWeek dayWeek) {
        this.dayWeek = dayWeek;
        load(activity, navController);
    }

    @Override
    protected void onLoad() {
        ManageEmployeesShiftViewModel viewModel;
        viewModel = activity.getViewModel(ManageEmployeesShiftViewModel.class);

        for (ShiftDto shift : dayWeek.getShiftList()) {
            ShiftItemView item = null;
            try {
                item = new ShiftItemView();
                item.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Objects.requireNonNull(item);
            item.getController().load(activity, navController, shift);
            shiftVBox.getChildren().add(item.getRoot());
        }

        if (dayWeek.getDate().isBefore(LocalDate.now().plusDays(1))) {
            addButton.setVisible(false);
        }
        addButton.setOnAction(a -> {
            viewModel.setSelectedDayWeek(dayWeek);
            try {
                navController.openInDialog(new NavDestination(new AssignShiftView()), "Assegna Turno");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}