package com.ttbmp.cinehub.ui.desktop.manageshift.table;


import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;
import org.kordamp.bootstrapfx.BootstrapFX;

/**
 * @author Massimo Mazzetti
 */
public class CalendarShiftItemView extends FxmlView {

    public CalendarShiftItemView() {
        super("manage_employee_shift/calendar_shift_item.fxml");
        addStylesheet("theme.css");
        addExternalStylesheet(BootstrapFX.bootstrapFXStylesheet());
    }

    @Override
    public CalendarShiftItemViewController getController() {
        return (CalendarShiftItemViewController) super.getController();
    }

}
