package com.ttbmp.cinehub.ui.desktop.appbar;

import com.ttbmp.cinehub.app.usecase.getuserrole.RoleRequest;
import com.ttbmp.cinehub.app.usecase.getuserrole.GetUserRoleHandler;
import com.ttbmp.cinehub.app.usecase.getuserrole.GetUserRoleUseCase;
import com.ttbmp.cinehub.app.usecase.logout.LogoutHandler;
import com.ttbmp.cinehub.app.usecase.logout.LogoutRequest;
import com.ttbmp.cinehub.app.usecase.logout.LogoutUseCase;
import com.ttbmp.cinehub.ui.desktop.CinehubApplication;
import com.ttbmp.cinehub.ui.desktop.about.AboutActivity;
import com.ttbmp.cinehub.ui.desktop.buyticket.BuyTicketActivity;
import com.ttbmp.cinehub.ui.desktop.login.LoginActivity;
import com.ttbmp.cinehub.ui.desktop.logout.LogoutActivity;
import com.ttbmp.cinehub.ui.desktop.manageshift.ManageShiftActivity;
import com.ttbmp.cinehub.ui.desktop.utilities.BindingHelper;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavActivityDestination;
import com.ttbmp.cinehub.ui.desktop.viewpersonalschedule.ViewPersonalScheduleActivity;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Fabio Buracchi
 */
public class AppBarViewController extends ViewController {

    private final AppBarViewModel viewModel = new AppBarViewModel();

    private final GetUserRoleUseCase getUserRoleUseCase = new GetUserRoleHandler(new AppBarPresenterFx(viewModel));
    private final LogoutUseCase logoutUseCase = new LogoutHandler(new AppBarPresenterFx(viewModel));

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab buyTicketTab;

    @FXML
    private Tab manageShiftTab;

    @FXML
    private Tab viewPersonalScheduleTab;

    @FXML
    private Tab loginTab;

    @FXML
    private Tab logoutTab;

    @FXML
    private Tab aboutTab;

    @Override
    protected void onLoad() {
        loadActivityTabMap();
        BindingHelper.bind(tabPane.getTabs(), viewModel.getTabList());
        getUserRoleUseCase.getUserRoles(new RoleRequest(CinehubApplication.getSessionToken()));
        tabPane.getSelectionModel().select(viewModel.getActivityTabMap().get(activity.getClass()));
        viewModel.getActivityTabMap().forEach((key, value) -> value.setOnSelectionChanged(handleTabSelection(key, value)));
    }

    private void loadActivityTabMap() {
        viewModel.getActivityTabMap().put(LoginActivity.class, loginTab);
        viewModel.getActivityTabMap().put(LogoutActivity.class, logoutTab);
        viewModel.getActivityTabMap().put(BuyTicketActivity.class, buyTicketTab);
        viewModel.getActivityTabMap().put(ManageShiftActivity.class, manageShiftTab);
        viewModel.getActivityTabMap().put(ViewPersonalScheduleActivity.class, viewPersonalScheduleTab);
        viewModel.getActivityTabMap().put(AboutActivity.class, aboutTab);
    }

    private EventHandler<Event> handleTabSelection(Class<? extends Activity> key, Tab value) {
        return event -> {
            if (value.isSelected()) {
                try {
                    if (key == LoginActivity.class) {
                        navController.navigate(new NavActivityDestination(key.getConstructor().newInstance()));
                    } else if (key == LogoutActivity.class) {
                        logoutUseCase.logout(new LogoutRequest(CinehubApplication.getSessionToken()));
                        navController.open(new NavActivityDestination(new BuyTicketActivity()));
                    } else {
                        navController.open(new NavActivityDestination(key.getConstructor().newInstance()));
                    }
                } catch (IOException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ioException) {
                    ioException.printStackTrace();
                }
            }
        };
    }

}
