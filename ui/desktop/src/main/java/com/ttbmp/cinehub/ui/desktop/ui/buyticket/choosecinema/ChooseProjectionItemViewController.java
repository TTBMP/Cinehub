package com.ttbmp.cinehub.ui.desktop.ui.buyticket.choosecinema;

import com.ttbmp.cinehub.app.dto.ProjectionDto;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Objects;

/**
 * @author Palmieri Ivan
 */
public class ChooseProjectionItemViewController extends ViewController {

    private ProjectionDto projectionDto;

    @FXML
    private Label projectionCapacityLabel;

    @FXML
    private Label freeSeatLabel;

    @FXML
    private Label buysSeatLabel;
    @FXML
    private Label projectionTimeLabel;

    @Override
    protected void onLoad() {
        Objects.requireNonNull(projectionDto);
        int freeSeat = 0;
        int buysSeat = 0;
        int totalSeat = projectionDto.getHallDto().getSeatList().size();
        for (int i = 0; i < projectionDto.getHallDto().getSeatList().size(); i++) {
            if (Boolean.TRUE.equals(projectionDto.getHallDto().getSeatList().get(i).getState())) {
                freeSeat++;
            } else {
                buysSeat++;
            }
        }
        freeSeatLabel.setText(String.valueOf(freeSeat));
        buysSeatLabel.setText(String.valueOf(buysSeat));
        projectionCapacityLabel.setText(String.valueOf(totalSeat));
        projectionTimeLabel.setText(projectionDto.getStartTime());

    }

    public void load(Activity activity, NavController navController, ProjectionDto projectionDto) {
        this.projectionDto = projectionDto;
        load(activity, navController);
    }

}
