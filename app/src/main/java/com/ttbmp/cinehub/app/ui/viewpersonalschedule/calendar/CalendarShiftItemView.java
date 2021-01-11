package com.ttbmp.cinehub.app.ui.viewpersonalschedule.calendar;

import com.ttbmp.cinehub.app.utilities.View;

import java.io.IOException;

/**
 * @author Fabio Buracchi
 */
public class CalendarShiftItemView extends View {

    public CalendarShiftItemView() throws IOException {
        super("calendar_shift_item.fxml");
    }

    @Override
    public CalendarShiftItemController getController() {
        return (CalendarShiftItemController) super.getController();
    }

}
