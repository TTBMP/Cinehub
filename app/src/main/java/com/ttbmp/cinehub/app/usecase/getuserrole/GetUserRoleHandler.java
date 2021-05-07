package com.ttbmp.cinehub.app.usecase.getuserrole;

import com.ttbmp.cinehub.app.di.ServiceLocator;

public class GetUserRoleHandler implements GetUserRoleUseCase {

    private final GetUserRoleController controller;

    public GetUserRoleHandler(GetUserRolePresenter presenter) {
        controller = new GetUserRoleController(new ServiceLocator(), presenter);
    }

    @Override
    public void getUserRoles(GetRoleRequest request) {
        controller.getUserRoles(request);
    }

}
