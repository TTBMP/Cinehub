package com.ttbmp.cinehub.app.ui.viewpersonalschedule.calendar;

import com.ttbmp.cinehub.app.CinehubApplication;
import com.ttbmp.cinehub.app.di.ViewPersonalScheduleContainer;
import com.ttbmp.cinehub.app.dto.ShiftDto;
import com.ttbmp.cinehub.app.ui.viewpersonalschedule.ViewPersonalScheduleViewModel;
import com.ttbmp.cinehub.app.ui.viewpersonalschedule.detail.ShiftDetailView;
import com.ttbmp.cinehub.app.utilities.ObjectBindings;
import com.ttbmp.cinehub.app.utilities.Utilities;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Fabio Buracchi
 */
public class CalendarShiftItemController {

    private final ViewPersonalScheduleContainer container = CinehubApplication.APP_CONTAINER.getContainer(
            ViewPersonalScheduleContainer.class
    );

    private final ViewPersonalScheduleViewModel viewModel = container.getViewModel();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox itemVBox;

    @FXML
    private Label startLabel;

    @FXML
    private Label endLabel;

    @FXML
    private Label locationLabel;

    @FXML
    void initialize() {
        assertProperInjection();
    }

    public void bind(ShiftDto shiftDto) {
        itemVBox.setOnMouseClicked(event -> {
            viewModel.selectedShiftProperty().setValue(shiftDto);
            try {
                Utilities.createPopup(new ShiftDetailView(), "Detail", Utilities.getStage(itemVBox));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        startLabel.textProperty().bind(ObjectBindings.map(shiftDto.startProperty(), localTime -> localTime.toString().replaceAll(":..\\..*$", "")));
        endLabel.textProperty().bind(ObjectBindings.map(shiftDto.endProperty(), localTime -> localTime.toString().replaceAll(":..\\..*$", "")));
        locationLabel.setText("via Roma");
    }

    private void assertProperInjection() {
        assert itemVBox != null : "fx:id=\"itemVBox\" was not injected: check your FXML file 'calendar_shift_item.fxml'.";
        assert startLabel != null : "fx:id=\"startLabel\" was not injected: check your FXML file 'calendar_shift_item.fxml'.";
        assert endLabel != null : "fx:id=\"endLabel\" was not injected: check your FXML file 'calendar_shift_item.fxml'.";
        assert locationLabel != null : "fx:id=\"locationLabel\" was not injected: check your FXML file 'calendar_shift_item.fxml'.";
    }
}
