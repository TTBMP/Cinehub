package com.ttbmp.cinehub.app.usecase.getuserrole;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.user.UserRepository;
import com.ttbmp.cinehub.app.service.security.SecurityService;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.domain.security.Permission;

import java.util.stream.Collectors;

public class GetUserRoleController implements GetUserRoleUseCase {

    private final GetUserRolePresenter presenter;
    private final UserRepository userRepository;
    private final SecurityService securityService;

    public GetUserRoleController(ServiceLocator serviceLocator, GetUserRolePresenter presenter) {
        this.presenter = presenter;
        this.userRepository = serviceLocator.getService(UserRepository.class);
        this.securityService = serviceLocator.getService(SecurityService.class);
    }

    @Override
    public void getUserRoles(RoleRequest request) {
        execute(presenter, request, () -> {
            var permissions = new Permission[]{};
            AuthenticatedRequest.validate(request, securityService, permissions);
            var user = userRepository.getUser(request.getUserId());
            var roleList = user.getRoleList().stream()
                    .map(RoleReply.Role::getRole)
                    .collect(Collectors.toList());
            presenter.present(new RoleReply(roleList));
        });
    }

}
