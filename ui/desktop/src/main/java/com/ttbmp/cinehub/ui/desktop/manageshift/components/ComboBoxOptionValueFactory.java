package com.ttbmp.cinehub.ui.desktop.manageshift.components;

import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ShiftRepeatingOption;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

/**
 * @author Massimo Mazzetti
 */
@SuppressWarnings("java:S110")
public class ComboBoxOptionValueFactory extends ListCell<ShiftRepeatingOption> {

    public ComboBoxOptionValueFactory(ListView<ShiftRepeatingOption> optionListView) {

    }

    @Override
    protected void updateItem(ShiftRepeatingOption item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            setGraphic(null);
        } else {
            setText(item.getValue());
        }
    }

}
