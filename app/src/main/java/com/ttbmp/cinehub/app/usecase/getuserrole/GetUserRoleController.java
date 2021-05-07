package com.ttbmp.cinehub.app.usecase.getuserrole;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.service.security.SecurityException;
import com.ttbmp.cinehub.app.service.security.SecurityService;

public class GetUserRoleController implements GetUserRoleUseCase {

    private final GetUserRolePresenter presenter;
    private final SecurityService securityService;

    public GetUserRoleController(ServiceLocator serviceLocator, GetUserRolePresenter presenter) {
        this.presenter = presenter;
        this.securityService = serviceLocator.getService(SecurityService.class);
    }

    @Override
    public void getUserRoles(GetRoleRequest request) {
        try {
            var user = securityService.authenticate(request.getSessionToken());
            presenter.present(user.getRoles());
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

}
