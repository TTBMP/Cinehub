package com.ttbmp.cinehub.ui.desktop.about;

import com.ttbmp.cinehub.ui.desktop.utilities.ui.FxmlView;
import org.kordamp.bootstrapfx.BootstrapFX;

/**
 * @author Ivan Palmieri
 */
public class AboutView extends FxmlView {

    public AboutView() {
        super("about_page.fxml");
        addStylesheet("theme.css");
        addExternalStylesheet(BootstrapFX.bootstrapFXStylesheet());
    }

}
