package com.ttbmp.cinehub.app.utilities.request;

import com.ttbmp.cinehub.app.service.security.SecurityException;
import com.ttbmp.cinehub.app.service.security.SecurityService;
import com.ttbmp.cinehub.domain.User;
import com.ttbmp.cinehub.domain.security.Permission;

public abstract class AuthenticatedRequest extends Request {

    private String userId;
    private String sessionToken;

    protected AuthenticatedRequest(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public static void validate(AuthenticatedRequest request, SecurityService securityService, Permission[] permissions)
            throws NullRequestException, InvalidRequestException, UnauthenticatedRequestException, UnauthorizedRequestException {
        User user;
        if (request == null) {
            throw new NullRequestException();
        }
        try {
            user = securityService.authenticate(request.sessionToken);
        } catch (SecurityException e) {
            throw new UnauthenticatedRequestException();
        }
        for (var permission : permissions) {
            if (!user.hasPermission(permission)) {
                throw new UnauthorizedRequestException();
            }
        }
        request.userId = user.getId();
        request.onValidate();
        if (!request.getErrorList().isEmpty()) {
            throw new InvalidRequestException();
        }
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static class UnauthenticatedRequestException extends Exception {

        public UnauthenticatedRequestException() {
            super("Unauthenticated request");
        }

    }

    public static class UnauthorizedRequestException extends Exception {

        public UnauthorizedRequestException() {
            super("Forbidden");
        }

    }

}
