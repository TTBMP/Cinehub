package com.ttbmp.cinehub.app.client.desktop.ui.manageshift.components;

import com.ttbmp.cinehub.core.entity.ShiftRepeatedEnum;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

/**
 * @author Massimo Mazzetti
 */

public class ComboBoxOptionValueFactory extends ListCell<ShiftRepeatedEnum> {



    public ComboBoxOptionValueFactory(ListView<ShiftRepeatedEnum> optionListView) {

    }

    @Override
    protected void updateItem(ShiftRepeatedEnum item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            setGraphic(null);
        } else {
            setText(item.getValue());
        }
    }

}
