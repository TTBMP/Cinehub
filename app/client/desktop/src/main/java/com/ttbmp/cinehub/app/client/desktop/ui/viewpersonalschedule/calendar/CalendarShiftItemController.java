package com.ttbmp.cinehub.app.client.desktop.ui.viewpersonalschedule.calendar;

import com.ttbmp.cinehub.app.client.desktop.dto.ShiftDto;
import com.ttbmp.cinehub.app.client.desktop.ui.viewpersonalschedule.ViewPersonalScheduleViewModel;
import com.ttbmp.cinehub.app.client.desktop.ui.viewpersonalschedule.detail.ShiftDetailView;
import com.ttbmp.cinehub.app.client.desktop.utilities.ObjectBindings;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.Controller;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.navigation.NavController;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.navigation.NavDestination;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * @author Fabio Buracchi
 */
public class CalendarShiftItemController extends Controller {

    @FXML
    private VBox itemVBox;

    @FXML
    private Label startLabel;

    @FXML
    private Label endLabel;

    @FXML
    private Label locationLabel;

    private ShiftDto shiftDto;

    @Override
    public void onLoad() {
        ViewPersonalScheduleViewModel viewModel = activity.getViewModel(ViewPersonalScheduleViewModel.class);
        itemVBox.setOnMouseClicked(event -> {
            viewModel.selectedShiftProperty().setValue(shiftDto);
            try {
                navController.openInDialog(new NavDestination(new ShiftDetailView()), "Detail");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        startLabel.textProperty().bind(ObjectBindings.map(shiftDto.startProperty(), localTime -> localTime.toString().replaceAll(":..\\..*$", "")));
        endLabel.textProperty().bind(ObjectBindings.map(shiftDto.endProperty(), localTime -> localTime.toString().replaceAll(":..\\..*$", "")));
        locationLabel.setText("via Roma");
    }

    public void load(Activity activity, NavController navController, ShiftDto shiftDto) {
        this.shiftDto = shiftDto;
        load(activity, navController);
    }

}
