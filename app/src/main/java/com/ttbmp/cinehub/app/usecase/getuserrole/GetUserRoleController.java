package com.ttbmp.cinehub.app.usecase.getuserrole;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.user.UserRepository;
import com.ttbmp.cinehub.app.service.security.SecurityService;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;
import com.ttbmp.cinehub.domain.security.Permission;

import java.util.Arrays;
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
        var permissions = new Permission[]{};
        try {
            AuthenticatedRequest.validate(request, securityService, permissions);
            var user = userRepository.getUser(request.getUserId());
            var roleList = Arrays.stream(user.getRoles())
                    .map(RoleResponse.Role::getRole)
                    .collect(Collectors.toList());
            presenter.present(new RoleResponse(roleList));
        } catch (Request.NullRequestException e) {
            presenter.presentNullRequest();
        } catch (Request.InvalidRequestException e) {
            presenter.presentInvalidRequest(request);
        } catch (AuthenticatedRequest.UnauthorizedRequestException e) {
            presenter.presentUnauthorizedError(e);
        } catch (AuthenticatedRequest.UnauthenticatedRequestException e) {
            presenter.presentUnauthenticatedError(e);
        } catch (RepositoryException e) {
            presenter.presentRepositoryError(e);
        }
    }

}
