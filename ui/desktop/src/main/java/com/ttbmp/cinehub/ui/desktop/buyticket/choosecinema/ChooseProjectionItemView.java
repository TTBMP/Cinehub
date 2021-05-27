package com.ttbmp.cinehub.ui.desktop.buyticket.choosecinema;

import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;

/**
 * @author Ivan Palmieri
 */
public class ChooseProjectionItemView extends FxmlView {

    public ChooseProjectionItemView() {
        super("buy_ticket/projection_list_item.fxml");
    }

    @Override
    public ChooseProjectionItemViewController getController() {
        return (ChooseProjectionItemViewController) super.getController();
    }
}
