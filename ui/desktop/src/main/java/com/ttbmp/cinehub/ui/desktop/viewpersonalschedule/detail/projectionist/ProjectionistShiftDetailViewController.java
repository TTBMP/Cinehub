package com.ttbmp.cinehub.ui.desktop.viewpersonalschedule.detail.projectionist;

import com.ttbmp.cinehub.app.dto.ProjectionDto;
import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.ProjectionListRequest;
import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.ViewPersonalScheduleUseCase;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.ui.desktop.viewpersonalschedule.ViewPersonalScheduleViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;

/**
 * @author Fabio Buracchi
 */
public class ProjectionistShiftDetailViewController extends ViewController {

    @FXML
    private Label dateLabel;

    @FXML
    private Label startLabel;

    @FXML
    private Label endLabel;

    @FXML
    private Label cityLabel;

    @FXML
    private Label roleLabel;

    @FXML
    private Label hallLabel;

    @FXML
    private ListView<ProjectionDto> projectionListView;

    @FXML
    private Button backButton;

    @Override
    protected void onLoad() {
        ViewPersonalScheduleViewModel viewModel = activity.getViewModel(ViewPersonalScheduleViewModel.class);
        projectionListView.itemsProperty().addListener(l -> projectionListView.refresh());
        projectionListView.setItems(viewModel.getProjectionList());
        projectionListView.setCellFactory(listView -> new ProjectionistListCell(activity, navController));
        dateLabel.textProperty().bind(viewModel.selectedShiftDateProperty());
        startLabel.textProperty().bind(viewModel.selectedShiftStartProperty());
        endLabel.textProperty().bind(viewModel.selectedShiftEndProperty());
        roleLabel.textProperty().bind(viewModel.selectedShiftEmployeeRoleProperty());
        cityLabel.textProperty().bind(viewModel.selectedShiftCinemaCityProperty());
        hallLabel.textProperty().bind(viewModel.selectedProjectionistShiftHallProperty());
        backButton.setOnAction(a -> {
            try {
                navController.popBackStack();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        activity.getUseCase(ViewPersonalScheduleUseCase.class).getShiftProjectionList(
                new ProjectionListRequest(viewModel.getSelectedShift())
        );
    }

}
