package com.ttbmp.cinehub.ui.desktop.buyticket.chooseseat;


import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;
import org.kordamp.bootstrapfx.BootstrapFX;

/**
 * @author Ivan Palmieri
 */
public class ChooseSeatView extends FxmlView {

    public ChooseSeatView() {
        super("buy_ticket/choose_seat.fxml");
        addStylesheet("theme.css");
        addExternalStylesheet(BootstrapFX.bootstrapFXStylesheet());
    }
}
