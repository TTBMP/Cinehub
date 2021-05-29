package com.ttbmp.cinehub.ui.desktop.buyticket.choosecinema;


import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;

/**
 * @author Ivan Palmieri
 */
public class ChooseCinemaItemView extends FxmlView {
    public ChooseCinemaItemView() {
        super("buy_ticket/cinema_list_item.fxml");
    }

    @Override
    public ChooseCinemaItemViewController getController() {
        return (ChooseCinemaItemViewController) super.getController();
    }

}
