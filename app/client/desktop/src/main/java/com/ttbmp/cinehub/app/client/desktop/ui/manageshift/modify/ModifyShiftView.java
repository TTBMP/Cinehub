package com.ttbmp.cinehub.app.client.desktop.ui.manageshift.modify;


import com.ttbmp.cinehub.app.client.desktop.utilities.ui.FxmlView;

import java.io.IOException;

/**
 * @author Massimo Mazzetti
 */
public class ModifyShiftView extends FxmlView {

    public ModifyShiftView() throws IOException {
        super("modify_shift.fxml");
        addStylesheet("styles.css");
    }

}
