package com.ttbmp.cinehub.ui.desktop.ui.manageshift.table;


import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;

import java.io.IOException;

/**
 * @author Massimo Mazzetti
 */
public class ShiftItemView extends FxmlView {

    public ShiftItemView() throws IOException {
        super("shift_item.fxml");
        addStylesheet("styles.css");

    }

    @Override
    public ShiftItemViewController getController() {
        return (ShiftItemViewController) super.getController();
    }

}
