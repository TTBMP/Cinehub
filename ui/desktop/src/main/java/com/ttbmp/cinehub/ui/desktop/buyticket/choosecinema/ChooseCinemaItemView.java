package com.ttbmp.cinehub.ui.desktop.buyticket.choosecinema;


import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;

import java.io.IOException;

/**
 * @author Palmieri Ivan
 */
public class ChooseCinemaItemView extends FxmlView {
    public ChooseCinemaItemView() throws IOException {
        super("cinema_list_item.fxml");
    }

    @Override
    public ChooseCinemaItemViewController getController() {
        return (ChooseCinemaItemViewController) super.getController();
    }

}
