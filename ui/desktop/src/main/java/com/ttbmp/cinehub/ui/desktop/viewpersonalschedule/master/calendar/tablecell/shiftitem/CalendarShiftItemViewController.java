package com.ttbmp.cinehub.ui.desktop.viewpersonalschedule.master.calendar.tablecell.shiftitem;

import com.ttbmp.cinehub.app.dto.shift.ShiftDto;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavController;
import com.ttbmp.cinehub.ui.desktop.viewpersonalschedule.ViewPersonalScheduleViewModel;
import com.ttbmp.cinehub.ui.desktop.viewpersonalschedule.detail.ShiftDetailView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

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
        var viewModel = activity.getViewModel(ViewPersonalScheduleViewModel.class);
        itemVBox.setOnMouseClicked(event -> {
            viewModel.selectedShiftProperty().setValue(shiftDto);
            navController.openViewInDialog(ShiftDetailView.class, "Detail");
        });
        startLabel.setText(shiftDto.getStart().toString());
        endLabel.setText(shiftDto.getEnd().toString());
        locationLabel.setText(viewModel.getCurrentEmployee().getCinema().getAddress());
    }

    public void load(Activity activity, NavController navController, ShiftDto shiftDto) {
        this.shiftDto = shiftDto;
        load(activity, navController);
    }

}
