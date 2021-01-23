package com.ttbmp.cinehub.app.client.desktop.ui.buyticket.choosecinema;




import com.ttbmp.cinehub.app.client.desktop.utilities.ui.FxmlView;

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
