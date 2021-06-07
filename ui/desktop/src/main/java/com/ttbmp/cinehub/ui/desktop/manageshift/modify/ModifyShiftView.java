package com.ttbmp.cinehub.ui.desktop.manageshift.modify;


import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;
import org.kordamp.bootstrapfx.BootstrapFX;

/**
 * @author Massimo Mazzetti
 */
public class ModifyShiftView extends FxmlView {

    public ModifyShiftView() {
        super("manage_employee_shift/modify_shift.fxml");
        addStylesheet("theme.css");
        addExternalStylesheet(BootstrapFX.bootstrapFXStylesheet());
    }

}
