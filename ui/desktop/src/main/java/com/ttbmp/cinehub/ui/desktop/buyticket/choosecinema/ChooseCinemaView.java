package com.ttbmp.cinehub.ui.desktop.buyticket.choosecinema;


import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;
import org.kordamp.bootstrapfx.BootstrapFX;

/**
 * @author Ivan Palmieri
 */
public class ChooseCinemaView extends FxmlView {

    public ChooseCinemaView() {
        super("buy_ticket/choose_cinema.fxml");
        addStylesheet("theme.css");
        addExternalStylesheet(BootstrapFX.bootstrapFXStylesheet());
    }
}
