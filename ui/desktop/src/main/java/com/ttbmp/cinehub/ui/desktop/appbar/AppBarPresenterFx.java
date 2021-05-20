package com.ttbmp.cinehub.ui.desktop.appbar;

import com.ttbmp.cinehub.app.usecase.getuserrole.GetUserRolePresenter;
import com.ttbmp.cinehub.app.usecase.getuserrole.RoleResponse;
import com.ttbmp.cinehub.app.usecase.logout.LogoutPresenter;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.ui.desktop.CinehubApplication;
import com.ttbmp.cinehub.ui.desktop.about.AboutActivity;
import com.ttbmp.cinehub.ui.desktop.buyticket.BuyTicketActivity;
import com.ttbmp.cinehub.ui.desktop.login.LoginActivity;
import com.ttbmp.cinehub.ui.desktop.logout.LogoutActivity;
import com.ttbmp.cinehub.ui.desktop.manageshift.ManageShiftActivity;
import com.ttbmp.cinehub.ui.desktop.viewpersonalschedule.ViewPersonalScheduleActivity;

import static com.ttbmp.cinehub.app.usecase.getuserrole.RoleResponse.Role.EMPLOYEE;
import static com.ttbmp.cinehub.app.usecase.getuserrole.RoleResponse.Role.MANAGER;

public class AppBarPresenterFx implements GetUserRolePresenter, LogoutPresenter {

    private final AppBarViewModel viewModel;

    public AppBarPresenterFx(AppBarViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void present(RoleResponse response) {
        viewModel.getTabList().clear();
        viewModel.getTabList().add(viewModel.getActivityTabMap().get(AboutActivity.class));
        viewModel.getTabList().add(viewModel.getActivityTabMap().get(LogoutActivity.class));
        if (response.getRoleList().contains(EMPLOYEE)) {
            viewModel.getTabList().add(viewModel.getActivityTabMap().get(ViewPersonalScheduleActivity.class));
        }
        if (response.getRoleList().contains(MANAGER)) {
            viewModel.getTabList().add(viewModel.getActivityTabMap().get(ManageShiftActivity.class));
        }
        viewModel.getTabList().add(viewModel.getActivityTabMap().get(BuyTicketActivity.class));
    }

    @Override
    public void presentUnauthenticatedError(AuthenticatedRequest.UnauthenticatedRequestException e) {
        viewModel.getTabList().clear();
        viewModel.getTabList().add(viewModel.getActivityTabMap().get(AboutActivity.class));
        viewModel.getTabList().add(viewModel.getActivityTabMap().get(LoginActivity.class));
        viewModel.getTabList().add(viewModel.getActivityTabMap().get(BuyTicketActivity.class));
    }

    @Override
    public void logout() {
        CinehubApplication.setSessionToken(null);
    }

}
