package com.ttbmp.cinehub.ui.desktop.ui.manageshift.table;


import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;

import java.io.IOException;

/**
 * @author Massimo Mazzetti
 */
public class CalendarShiftItemView extends FxmlView {

    public CalendarShiftItemView() throws IOException {
        super("calendar_shift_item_massimo.fxml");
        addStylesheet("styles.css");

    }

    @Override
    public CalendarShiftItemViewController getController() {
        return (CalendarShiftItemViewController) super.getController();
    }

}
