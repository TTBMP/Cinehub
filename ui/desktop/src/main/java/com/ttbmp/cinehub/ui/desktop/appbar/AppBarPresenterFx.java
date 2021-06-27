package com.ttbmp.cinehub.ui.desktop.appbar;

import com.ttbmp.cinehub.app.CinehubException;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.usecase.getuserrole.GetUserRolePresenter;
import com.ttbmp.cinehub.app.usecase.getuserrole.RoleReply;
import com.ttbmp.cinehub.app.usecase.logout.LogoutPresenter;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;
import com.ttbmp.cinehub.ui.desktop.CinehubApplication;
import com.ttbmp.cinehub.ui.desktop.about.AboutActivity;
import com.ttbmp.cinehub.ui.desktop.buyticket.BuyTicketActivity;
import com.ttbmp.cinehub.ui.desktop.login.LoginActivity;
import com.ttbmp.cinehub.ui.desktop.logout.LogoutActivity;
import com.ttbmp.cinehub.ui.desktop.manageshift.ManageShiftActivity;
import com.ttbmp.cinehub.ui.desktop.viewpersonalschedule.ViewPersonalScheduleActivity;

import java.util.stream.Collectors;

import static com.ttbmp.cinehub.app.usecase.getuserrole.RoleReply.Role.EMPLOYEE;
import static com.ttbmp.cinehub.app.usecase.getuserrole.RoleReply.Role.MANAGER;

public class AppBarPresenterFx implements GetUserRolePresenter, LogoutPresenter {

    private final AppBarViewModel viewModel;

    public AppBarPresenterFx(AppBarViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void present(RoleReply reply) {
        viewModel.getTabList().clear();
        viewModel.getTabList().add(viewModel.getActivityTabMap().get(null));
        viewModel.getTabList().add(viewModel.getActivityTabMap().get(BuyTicketActivity.class));
        if (reply.getRoleList().contains(EMPLOYEE)) {
            viewModel.getTabList().add(viewModel.getActivityTabMap().get(ViewPersonalScheduleActivity.class));
        }
        if (reply.getRoleList().contains(MANAGER)) {
            viewModel.getTabList().add(viewModel.getActivityTabMap().get(ManageShiftActivity.class));
        }
        viewModel.getTabList().add(viewModel.getActivityTabMap().get(AboutActivity.class));
        viewModel.getTabList().add(viewModel.getActivityTabMap().get(LogoutActivity.class));
    }

    @Override
    public void logout() {
        CinehubApplication.setSessionToken(null);
    }

    @Override
    public void presentInvalidRequest(Request request) {
        viewModel.setErrorMessage(request.getErrorList().stream()
                .map(Request.Error::getMessage)
                .collect(Collectors.joining())
        );
    }

    @Override
    public void presentNullRequest() {
        viewModel.setErrorMessage("Request can't be null");
    }

    @Override
    public void presentApplicationError(CinehubException e) {
        viewModel.setErrorMessage(e.getMessage());
    }

    @Override
    public void presentUnauthorizedError(AuthenticatedRequest.UnauthorizedRequestException e) {
        viewModel.setErrorMessage(e.getMessage());
    }

    @Override
    public void presentUnauthenticatedError(AuthenticatedRequest.UnauthenticatedRequestException e) {
        viewModel.getTabList().clear();
        viewModel.getTabList().add(viewModel.getActivityTabMap().get(null));
        viewModel.getTabList().add(viewModel.getActivityTabMap().get(BuyTicketActivity.class));
        viewModel.getTabList().add(viewModel.getActivityTabMap().get(AboutActivity.class));
        viewModel.getTabList().add(viewModel.getActivityTabMap().get(LoginActivity.class));
    }

}
