package com.ttbmp.cinehub.ui.desktop.manageshift.table;


import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;

/**
 * @author Massimo Mazzetti
 */
public class CalendarShiftItemView extends FxmlView {

    public CalendarShiftItemView() {
        super("calendar_shift_item_massimo.fxml");
        addStylesheet("styles.css");

    }

    @Override
    public CalendarShiftItemViewController getController() {
        return (CalendarShiftItemViewController) super.getController();
    }

}
