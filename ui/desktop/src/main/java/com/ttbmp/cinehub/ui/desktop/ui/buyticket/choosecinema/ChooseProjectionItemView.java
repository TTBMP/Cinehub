package com.ttbmp.cinehub.ui.desktop.ui.buyticket.choosecinema;

import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;

import java.io.IOException;

/**
 * @author Palmieri Ivan
 */
public class ChooseProjectionItemView extends FxmlView {

    public ChooseProjectionItemView() throws IOException {
        super("projection_list_item.fxml");
    }

    @Override
    public ChooseProjectionItemViewController getController() {
        return (ChooseProjectionItemViewController) super.getController();
    }
}
