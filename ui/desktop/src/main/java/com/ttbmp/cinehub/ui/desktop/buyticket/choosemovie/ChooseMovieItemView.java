package com.ttbmp.cinehub.ui.desktop.buyticket.choosemovie;


import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;

import java.io.IOException;

/**
 * @author Palmieri Ivan
 */
public class ChooseMovieItemView extends FxmlView {
    public ChooseMovieItemView() throws IOException {
        super("buy_ticket/movie_list_item.fxml");
    }

    @Override
    public ChooseMovieItemViewController getController() {
        return (ChooseMovieItemViewController) super.getController();
    }
}
