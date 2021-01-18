package com.ttbmp.cinehub.app.client.desktop.utilities.ui.stage;

import javafx.stage.Modality;
import javafx.stage.Stage;

public class DialogStage extends Stage {

    public DialogStage(Stage owner, String title) {
        super();
        initModality(Modality.APPLICATION_MODAL);
        initOwner(owner);
        setTitle(title);
    }
}
