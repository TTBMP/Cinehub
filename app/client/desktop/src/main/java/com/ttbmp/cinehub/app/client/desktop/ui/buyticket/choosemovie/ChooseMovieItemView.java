package com.ttbmp.cinehub.app.client.desktop.ui.buyticket.choosemovie;



import com.ttbmp.cinehub.app.client.desktop.utilities.ui.FxmlView;

import java.io.IOException;

/**
 * @author Palmieri Ivan
 */
public class ChooseMovieItemView extends FxmlView {
    public ChooseMovieItemView() throws IOException {
        super("movie_list_item.fxml");
    }

    @Override
    public ChooseMovieItemViewController getController() {
        return (ChooseMovieItemViewController) super.getController();
    }
}
