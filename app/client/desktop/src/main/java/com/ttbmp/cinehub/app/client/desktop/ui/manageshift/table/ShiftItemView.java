package com.ttbmp.cinehub.app.client.desktop.ui.manageshift.table;


import com.ttbmp.cinehub.app.client.desktop.utilities.ui.FxmlView;

import java.io.IOException;

public class ShiftItemView extends FxmlView {

    public ShiftItemView() throws IOException {
        super("shift_item.fxml");

    }

    @Override
    public ShiftItemViewController getController() {
        return (ShiftItemViewController) super.getController();
    }
}
