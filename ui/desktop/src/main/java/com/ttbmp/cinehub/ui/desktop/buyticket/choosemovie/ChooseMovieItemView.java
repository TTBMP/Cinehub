package com.ttbmp.cinehub.ui.desktop.buyticket.choosemovie;


import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;

/**
 * @author Ivan Palmieri
 */
public class ChooseMovieItemView extends FxmlView {
    public ChooseMovieItemView() {
        super("buy_ticket/movie_list_item.fxml");
    }

    @Override
    public ChooseMovieItemViewController getController() {
        return (ChooseMovieItemViewController) super.getController();
    }
}
