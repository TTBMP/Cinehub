package com.ttbmp.cinehub.app.client.desktop.ui.viewpersonalschedule.calendar;

import com.ttbmp.cinehub.app.client.desktop.dto.ShiftDto;
import com.ttbmp.cinehub.app.client.desktop.utilities.ObjectBindings;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.Controller;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Fabio Buracchi
 */
public class CalendarTableCellController extends Controller {

    @FXML
    private VBox shiftVBox;

    @FXML
    private Label coworkerNumberLabel;

    @FXML
    private Label dayNumberLabel;

    public void bind(CalendarDay calendarDay) {
        dayNumberLabel.textProperty().bind(ObjectBindings.map(calendarDay.dateProperty(), date -> Integer.toString(date.getDayOfMonth())));
        calendarDay.getDateShiftList().addListener((ListChangeListener<ShiftDto>) c -> updateShiftVBox(calendarDay));
        coworkerNumberLabel.setText("0");
        updateShiftVBox(calendarDay);
    }

    private void updateShiftVBox(CalendarDay calendarDay) {
        shiftVBox.getChildren().clear();
        for (ShiftDto shiftDto : calendarDay.getDateShiftList()) {
            CalendarShiftItemView itemView = null;
            try {
                itemView = new CalendarShiftItemView();
                itemView.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Objects.requireNonNull(itemView);
            shiftVBox.getChildren().add(itemView.getRoot());
            itemView.getController().setNavController(navController);
            itemView.getController().setActivity(activity);
            itemView.getController().bind(shiftDto);
        }
    }

    @Override
    public void onLoad() {

    }

}
