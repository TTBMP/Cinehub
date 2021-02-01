package com.ttbmp.cinehub.app.client.desktop.ui.buyticket.choosecinema;

import com.ttbmp.cinehub.app.client.desktop.utilities.ui.FxmlView;

import java.io.IOException;

public class ChooseProjectionItemView extends FxmlView {

    public ChooseProjectionItemView() throws IOException {
        super("projection_list_item.fxml");
    }

    @Override
    public ChooseProjectionItemViewController getController() {
        return (ChooseProjectionItemViewController) super.getController();
    }
}
