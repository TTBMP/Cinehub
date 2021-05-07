package com.ttbmp.cinehub.ui.desktop.appbar;

import com.ttbmp.cinehub.app.service.security.Role;
import com.ttbmp.cinehub.app.usecase.getuserrole.GetUserRolePresenter;
import com.ttbmp.cinehub.app.usecase.logout.LogoutPresenter;
import com.ttbmp.cinehub.ui.desktop.CinehubApplication;
import com.ttbmp.cinehub.ui.desktop.about.AboutActivity;
import com.ttbmp.cinehub.ui.desktop.buyticket.BuyTicketActivity;
import com.ttbmp.cinehub.ui.desktop.login.LoginActivity;
import com.ttbmp.cinehub.ui.desktop.logout.LogoutActivity;
import com.ttbmp.cinehub.ui.desktop.manageshift.ManageShiftActivity;
import com.ttbmp.cinehub.ui.desktop.viewpersonalschedule.ViewPersonalScheduleActivity;

import java.util.Arrays;

public class AppBarPresenterFx implements GetUserRolePresenter, LogoutPresenter {

    private final AppBarViewModel viewModel;

    public AppBarPresenterFx(AppBarViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void present(Role[] roles) {
        viewModel.getTabList().clear();
        viewModel.getTabList().add(viewModel.getActivityTabMap().get(AboutActivity.class));
        if (Arrays.equals(roles, new Role[]{Role.GUEST_ROLE})) {
            viewModel.getTabList().add(viewModel.getActivityTabMap().get(LoginActivity.class));
        } else {
            viewModel.getTabList().add(viewModel.getActivityTabMap().get(LogoutActivity.class));
            if (Arrays.asList(roles).contains(Role.EMPLOYEE_ROLE)) {
                viewModel.getTabList().add(viewModel.getActivityTabMap().get(ViewPersonalScheduleActivity.class));
            }
            if (Arrays.asList(roles).contains(Role.MANAGER_ROLE)) {
                viewModel.getTabList().add(viewModel.getActivityTabMap().get(ManageShiftActivity.class));
            }
        }
        viewModel.getTabList().add(viewModel.getActivityTabMap().get(BuyTicketActivity.class));
    }

    @Override
    public void logout() {
        CinehubApplication.setSessionToken("");
    }

}
