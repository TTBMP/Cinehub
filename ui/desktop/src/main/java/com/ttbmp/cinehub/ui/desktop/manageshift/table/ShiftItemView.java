package com.ttbmp.cinehub.ui.desktop.manageshift.table;


import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;

/**
 * @author Massimo Mazzetti
 */
public class ShiftItemView extends FxmlView {

    public ShiftItemView() {
        super("manage_employee_shift/shift_item.fxml");
        addStylesheet("styles.css");

    }

    @Override
    public ShiftItemViewController getController() {
        return (ShiftItemViewController) super.getController();
    }

}
