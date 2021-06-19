package com.ttbmp.cinehub.ui.desktop.manageshift.table;

import com.ttbmp.cinehub.ui.desktop.manageshift.ManageEmployeesShiftViewModel;
import com.ttbmp.cinehub.ui.desktop.manageshift.assign.AssignShiftView;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavController;
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

    Day day;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private VBox shiftVBox;
    @FXML
    private Button addButton;

    public void load(Activity activity, NavController navController, Day day) {
        this.day = day;
        load(activity, navController);
    }

    @Override
    protected void onLoad() {
        ManageEmployeesShiftViewModel viewModel;
        viewModel = activity.getViewModel(ManageEmployeesShiftViewModel.class);

        for (var shift : day.getShiftList()) {
            ShiftItemView item = null;
            try {
                item = new ShiftItemView();
                item.load();
            } catch (IOException e) {
                navController.openErrorDialog(e.getMessage(), true);
            }
            Objects.requireNonNull(item);
            item.getController().load(activity, navController, shift);
            shiftVBox.getChildren().add(item.getRoot());
        }

        if (day.getDate().isBefore(LocalDate.now().plusDays(1))) {
            addButton.setVisible(false);
        }
        addButton.setOnAction(a -> {
            viewModel.selectedDayWeekProperty().set(day);
            viewModel.getHallList().setAll(day.getEmployee().getCinema().getHalList());
            navController.openViewInDialog(AssignShiftView.class, "Assign shift");
        });
    }

}