package com.ttbmp.cinehub.app.usecase.getuserrole;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.service.security.SecurityException;
import com.ttbmp.cinehub.app.service.security.SecurityService;

import java.util.Arrays;
import java.util.stream.Collectors;

public class GetUserRoleController implements GetUserRoleUseCase {

    private final GetUserRolePresenter presenter;
    private final SecurityService securityService;

    public GetUserRoleController(ServiceLocator serviceLocator, GetUserRolePresenter presenter) {
        this.presenter = presenter;
        this.securityService = serviceLocator.getService(SecurityService.class);
    }

    @Override
    public void getUserRoles(RoleRequest request) {
        try {
            var user = securityService.authenticate(request.getSessionToken());
            var roleList = Arrays.stream(user.getRoles())
                    .map(RoleResponse.Role::getRole)
                    .collect(Collectors.toList());
            presenter.present(new RoleResponse(roleList));
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

}
