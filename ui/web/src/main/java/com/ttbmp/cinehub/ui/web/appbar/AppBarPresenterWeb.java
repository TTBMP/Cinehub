package com.ttbmp.cinehub.ui.web.appbar;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.usecase.getuserrole.GetUserRolePresenter;
import com.ttbmp.cinehub.app.usecase.getuserrole.RoleResponse;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;
import com.ttbmp.cinehub.ui.web.utilities.ErrorHelper;

import java.util.Map;

import static com.ttbmp.cinehub.app.usecase.getuserrole.RoleResponse.Role.EMPLOYEE;
import static com.ttbmp.cinehub.app.usecase.getuserrole.RoleResponse.Role.MANAGER;

public class AppBarPresenterWeb implements GetUserRolePresenter {

    private final Map<String, Object> model;

    public AppBarPresenterWeb(Map<String, Object> model) {
        this.model = model;
    }

    @Override
    public void present(RoleResponse response) {
        model.put("is_about_tab_visible", true);
        model.put("is_logout_tab_visible", true);
        model.put("is_buy_ticket_tab_visible", true);
        model.put("is_view_personal_schedule_tab_visible", response.getRoleList().contains(EMPLOYEE));
        model.put("is_manage_shift_tab_visible", response.getRoleList().contains(MANAGER));
    }

    @Override
    public void presentInvalidRequest(Request request) {
        model.put(ErrorHelper.ERROR_ATTRIBUTE_NAME, ErrorHelper.getRequestErrorMessage(request));
    }

    @Override
    public void presentNullRequest() {
        model.put(ErrorHelper.ERROR_ATTRIBUTE_NAME, ErrorHelper.INVALID_ERROR_MESSAGE);
    }

    @Override
    public void presentRepositoryError(RepositoryException e) {
        model.put(ErrorHelper.ERROR_ATTRIBUTE_NAME, e.getMessage());
    }

    @Override
    public void presentUnauthorizedError(AuthenticatedRequest.UnauthorizedRequestException e) {
        model.put(ErrorHelper.ERROR_ATTRIBUTE_NAME, e.getMessage());
    }

    @Override
    public void presentUnauthenticatedError(AuthenticatedRequest.UnauthenticatedRequestException e) {
        model.put("is_about_tab_visible", true);
        model.put("is_login_tab_visible", true);
        model.put("is_buy_ticket_tab_visible", true);
    }

}
