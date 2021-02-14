package com.ttbmp.cinehub.ui.desktop.about;

import com.ttbmp.cinehub.ui.desktop.appbar.AppBarViewController;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewController;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

/**
 * @author Palmieri Ivan
 */
public class AboutViewController extends ViewController {

    @FXML
    private VBox appBar;

    @FXML
    private AppBarViewController appBarController;

    @Override
    protected void onLoad() {
        appBarController.load(activity, navController);
    }

}
