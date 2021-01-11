package com.ttbmp.cinehub.app.ui.viewpersonalschedule.calendar;

import com.ttbmp.cinehub.app.dto.ShiftDto;
import com.ttbmp.cinehub.app.utilities.ObjectBindings;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author Fabio Buracchi
 */
public class CalendarTableCellController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox shiftVBox;

    @FXML
    private Label coworkerNumberLabel;

    @FXML
    private Label dayNumberLabel;

    @FXML
    void initialize() {
        assertProperInjection();
    }

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
            } catch (IOException e) {
                e.printStackTrace();
            }
            Objects.requireNonNull(itemView);
            shiftVBox.getChildren().add(itemView.getRoot());
            itemView.getController().bind(shiftDto);
        }
    }

    private void assertProperInjection() {
        assert shiftVBox != null : "fx:id=\"shiftVBox\" was not injected: check your FXML file 'calendar_table_cell.fxml'.";
        assert coworkerNumberLabel != null : "fx:id=\"coworkerNumberLabel\" was not injected: check your FXML file 'calendar_table_cell.fxml'.";
        assert dayNumberLabel != null : "fx:id=\"dayNumberLabel\" was not injected: check your FXML file 'calendar_table_cell.fxml'.";
    }

}
