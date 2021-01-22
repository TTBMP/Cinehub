package com.ttbmp.cinehub.app.client.desktop.ui.viewpersonalschedule.master.calendar.tablecell.shiftitem;

import com.ttbmp.cinehub.app.client.desktop.ui.viewpersonalschedule.ViewPersonalScheduleViewModel;
import com.ttbmp.cinehub.app.client.desktop.ui.viewpersonalschedule.detail.ShiftDetailView;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.navigation.NavController;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.navigation.NavDestination;
import com.ttbmp.cinehub.core.dto.ShiftDto;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * @author Fabio Buracchi
 */
public class CalendarShiftItemViewController extends ViewController {

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
    protected void onLoad() {
        ViewPersonalScheduleViewModel viewModel = activity.getViewModel(ViewPersonalScheduleViewModel.class);
        itemVBox.setOnMouseClicked(event -> {
            viewModel.selectedShiftProperty().setValue(shiftDto);
            try {
                navController.openInDialog(new NavDestination(new ShiftDetailView()), "Detail");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        startLabel.setText(shiftDto.getStart().toString());
        endLabel.setText(shiftDto.getEnd().toString());
        locationLabel.setText("via Roma");
    }

    public void load(Activity activity, NavController navController, ShiftDto shiftDto) {
        this.shiftDto = shiftDto;
        load(activity, navController);
    }

}
