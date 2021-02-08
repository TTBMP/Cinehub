package com.ttbmp.cinehub.ui.desktop.ui.viewpersonalschedule.master.calendar.tablecell;

import com.ttbmp.cinehub.app.dto.ShiftDto;
import com.ttbmp.cinehub.ui.desktop.ui.viewpersonalschedule.master.calendar.CalendarDay;
import com.ttbmp.cinehub.ui.desktop.ui.viewpersonalschedule.master.calendar.tablecell.shiftitem.CalendarShiftItemView;
import com.ttbmp.cinehub.ui.desktop.utilities.ObjectBindings;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavController;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Fabio Buracchi
 */
public class CalendarTableCellViewController extends ViewController {

    @FXML
    private VBox shiftVBox;

    @FXML
    private Label coworkerNumberLabel;

    @FXML
    private Label dayNumberLabel;

    private CalendarDay calendarDay;

    @Override
    protected void onLoad() {
        dayNumberLabel.textProperty().bind(ObjectBindings.map(calendarDay.dateProperty(), date -> Integer.toString(date.getDayOfMonth())));
        calendarDay.getDateShiftList().addListener((ListChangeListener<ShiftDto>) c -> updateShiftVBox(calendarDay));
        coworkerNumberLabel.setText("0");
        updateShiftVBox(calendarDay);
    }

    public void load(Activity activity, NavController navController, CalendarDay calendarDay) {
        this.calendarDay = calendarDay;
        load(activity, navController);
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
            itemView.getController().load(activity, navController, shiftDto);
        }
    }

}
