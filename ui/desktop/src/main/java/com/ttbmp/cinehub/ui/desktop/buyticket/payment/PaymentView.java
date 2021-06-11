package com.ttbmp.cinehub.ui.desktop.buyticket.payment;


import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;
import org.kordamp.bootstrapfx.BootstrapFX;

/**
 * @author Ivan Palmieri
 */
public class PaymentView extends FxmlView {

    public PaymentView() {
        super("buy_ticket/payment.fxml");
        addStylesheet("theme.css");
        addExternalStylesheet(BootstrapFX.bootstrapFXStylesheet());
    }
}
