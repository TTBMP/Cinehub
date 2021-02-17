package com.ttbmp.cinehub.app.utilities.request;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.user.UserRepository;
import com.ttbmp.cinehub.app.service.authentication.AuthenticationException;
import com.ttbmp.cinehub.app.service.authentication.AuthenticationService;
import com.ttbmp.cinehub.domain.user.Permission;
import com.ttbmp.cinehub.domain.user.User;

public abstract class AuthenticatedRequest extends Request {

    private User user;
    private String sessionToken;

    protected AuthenticatedRequest(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static void validate(AuthenticatedRequest request, Permission[] permissions, ServiceLocator serviceLocator)
            throws NullRequestException, InvalidRequestException, UnauthenticatedRequestException, UnauthorizedRequestException {
        if (request == null) {
            throw new NullRequestException();
        }
        try {
            request.user = serviceLocator.getService(UserRepository.class).getUser(
                    serviceLocator.getService(AuthenticationService.class).authenticate(request.sessionToken)
            );
        } catch (AuthenticationException e) {
            throw new UnauthenticatedRequestException();
        }
        for (Permission permission : permissions) {
            if (!request.user.hasPermission(permission)) {
                throw new UnauthorizedRequestException();
            }
        }
        request.onValidate();
        if (!request.getErrorList().isEmpty()) {
            throw new InvalidRequestException();
        }
    }

    public static class UnauthenticatedRequestException extends Exception {

    }

    public static class UnauthorizedRequestException extends Exception {

    }

}
