package com.ttbmp.cinehub.ui.desktop.manageshift.assign;


import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;
import org.kordamp.bootstrapfx.BootstrapFX;

/**
 * @author Massimo Mazzetti
 */
public class AssignShiftView extends FxmlView {

    public AssignShiftView() {
        super("manage_employee_shift/assign_shift.fxml");
        addStylesheet("theme.css");
        addExternalStylesheet(BootstrapFX.bootstrapFXStylesheet());
    }

}
