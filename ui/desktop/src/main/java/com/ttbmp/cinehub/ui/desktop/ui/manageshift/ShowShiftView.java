package com.ttbmp.cinehub.ui.desktop.ui.manageshift;

import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;

import java.io.IOException;

/**
 * @author Massimo Mazzetti
 */
public class ShowShiftView extends FxmlView {

    protected ShowShiftView() throws IOException {
        super("show_shift.fxml");
        addStylesheet("styles.css");
    }
}
