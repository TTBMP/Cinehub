package com.ttbmp.cinehub.ui.desktop.ui.buyticket.choosecinema;

import com.ttbmp.cinehub.domain.dto.CinemaDto;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.util.Objects;

/**
 * @author Palmieri Ivan
 */
public class ChooseCinemaItemViewController extends ViewController {

    private CinemaDto cinemaDto;

    @FXML
    private Label nameCinemaLabel;

    @FXML
    private Text cinemaAddressText;

    @FXML
    private Text cinemaCityText;

    @Override
    protected void onLoad() {
        Objects.requireNonNull(cinemaDto);
        cinemaAddressText.setText(cinemaDto.getAddress());
        cinemaCityText.setText(cinemaDto.getCity());
        nameCinemaLabel.setText(cinemaDto.getName());
    }

    public void load(Activity activity, NavController navController, CinemaDto cinemaDto) {
        this.cinemaDto = cinemaDto;
        load(activity, navController);
    }
}
