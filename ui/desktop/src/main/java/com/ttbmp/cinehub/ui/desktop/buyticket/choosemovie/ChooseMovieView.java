package com.ttbmp.cinehub.ui.desktop.buyticket.choosemovie;


import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;
import org.kordamp.bootstrapfx.BootstrapFX;

/**
 * @author Ivan Palmieri
 */
public class ChooseMovieView extends FxmlView {

    public ChooseMovieView() {
        super("buy_ticket/choose_movie.fxml");
        addStylesheet("theme.css");
        addExternalStylesheet(BootstrapFX.bootstrapFXStylesheet());
    }
}
