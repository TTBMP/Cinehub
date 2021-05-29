package com.ttbmp.cinehub.ui.desktop.manageshift.table;

import com.ttbmp.cinehub.app.dto.employee.UsherDto;
import com.ttbmp.cinehub.app.dto.shift.ShiftDto;
import com.ttbmp.cinehub.ui.desktop.manageshift.ManageEmployeesShiftViewModel;
import com.ttbmp.cinehub.ui.desktop.manageshift.detail.ShowShiftDetailView;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavController;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavDestination;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Massimo Mazzetti
 */
public class ShiftItemViewController extends ViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label startLabel;

    @FXML
    private Label endLabel;

    @FXML
    private HBox shiftHBox;

    private ShiftDto shift;

    @Override
    protected void onLoad() {
        ManageEmployeesShiftViewModel viewModel;
        viewModel = activity.getViewModel(ManageEmployeesShiftViewModel.class);
        if (viewModel.getEmployee(shift) instanceof UsherDto) {
            shiftHBox.setStyle("-fx-background-color: #FFFF00;");
        }

        startLabel.setText(shift.getStart().toString());
        endLabel.setText(shift.getEnd().toString());

        shiftHBox.setOnMouseClicked(l -> {
            viewModel.selectedDaysProperty().setValue(shift.getDate());
            viewModel.selectedShiftProperty().setValue(shift);
            navController.openInDialog(new NavDestination(new ShowShiftDetailView()), "Shift detail");
        });
    }


    public void load(Activity activity, NavController controller, ShiftDto shiftDto) {
        this.shift = shiftDto;
        load(activity, controller);
    }

}



