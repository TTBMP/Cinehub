package com.ttbmp.cinehub.ui.desktop.buyticket.choosecinema;

import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;

import java.io.IOException;

/**
 * @author Palmieri Ivan
 */
public class ChooseProjectionItemView extends FxmlView {

    public ChooseProjectionItemView() throws IOException {
        super("buy_ticket/projection_list_item.fxml");
    }

    @Override
    public ChooseProjectionItemViewController getController() {
        return (ChooseProjectionItemViewController) super.getController();
    }
}
