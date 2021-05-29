package com.ttbmp.cinehub.ui.desktop.utilities.ui.stage;

import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Fabio Buracchi
 */
public class DialogStage extends Stage {

    public DialogStage(Stage owner, String title) {
        super();
        initModality(Modality.APPLICATION_MODAL);
        initOwner(owner);
        setTitle(title);
    }

}
