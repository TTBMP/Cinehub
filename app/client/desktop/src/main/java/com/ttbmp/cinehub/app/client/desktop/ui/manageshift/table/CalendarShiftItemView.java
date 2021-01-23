package com.ttbmp.cinehub.app.client.desktop.ui.manageshift.table;



import com.ttbmp.cinehub.app.client.desktop.utilities.ui.FxmlView;

import java.io.IOException;

public class CalendarShiftItemView extends FxmlView {

    public CalendarShiftItemView() throws IOException {
        super("calendar_shift_item_massimo.fxml");

    }

    @Override
    public CalendarShiftItemViewController getController() {
        return (CalendarShiftItemViewController) super.getController();
    }

}
