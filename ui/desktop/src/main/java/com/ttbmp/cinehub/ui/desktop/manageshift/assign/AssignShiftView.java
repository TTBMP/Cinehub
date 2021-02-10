package com.ttbmp.cinehub.ui.desktop.manageshift.assign;


import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;

import java.io.IOException;

/**
 * @author Massimo Mazzetti
 */
public class AssignShiftView extends FxmlView {

    public AssignShiftView() throws IOException {
        super("assign_shift.fxml");
        addStylesheet("styles.css");
    }

}
