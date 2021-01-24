package com.ttbmp.cinehub.app.client.desktop.ui.manageshift.factory;

import com.ttbmp.cinehub.app.client.desktop.ui.manageshift.assign.Option;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

/**
 * @author Massimo Mazzetti
 */

public class ComboBoxOptionValueFactory extends ListCell<Option> {

    private ListView<Option> optionListView;

    public ComboBoxOptionValueFactory(ListView<Option> optionListView) {
        this.optionListView = optionListView;
    }

    @Override
    protected void updateItem(Option item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            setGraphic(null);
        } else {
            setText(item.getValue());
        }
    }

    public ListView<Option> getOptionListView() {
        return optionListView;
    }

    public void setOptionListView(ListView<Option> optionListView) {
        this.optionListView = optionListView;
    }
}
