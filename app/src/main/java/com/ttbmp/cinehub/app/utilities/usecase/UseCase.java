package com.ttbmp.cinehub.app.utilities.usecase;

import com.ttbmp.cinehub.app.CinehubException;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.service.security.SecurePresenter;
import com.ttbmp.cinehub.app.utilities.presenter.Presenter;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;

/**
 * @author Fabio Buracchi
 */
public interface UseCase {

    default void execute(SecurePresenter presenter, Request request, UseCaseSecureService service) {
        try {
            service.serve();
        } catch (Request.NullRequestException e) {
            presenter.presentNullRequest();
        } catch (Request.InvalidRequestException e) {
            presenter.presentInvalidRequest(request);
        } catch (AuthenticatedRequest.UnauthorizedRequestException e) {
            presenter.presentUnauthorizedError(e);
        } catch (AuthenticatedRequest.UnauthenticatedRequestException e) {
            presenter.presentUnauthenticatedError(e);
        } catch (Exception e) {
            presenter.presentApplicationError(new CinehubException(e.getMessage()));
        }
    }

    default void execute(Presenter presenter, Request request, UseCaseService service) {
        try {
            service.serve();
        } catch (Request.NullRequestException e) {
            presenter.presentNullRequest();
        } catch (Request.InvalidRequestException e) {
            presenter.presentInvalidRequest(request);
        } catch (Exception e) {
            presenter.presentApplicationError(new CinehubException(e.getMessage()));
        }
    }

    @FunctionalInterface
    interface UseCaseService {
        void serve()
                throws
                Request.NullRequestException,
                Request.InvalidRequestException,
                RepositoryException;
    }

    @FunctionalInterface
    interface UseCaseSecureService {
        void serve()
                throws
                Request.NullRequestException,
                Request.InvalidRequestException,
                AuthenticatedRequest.UnauthorizedRequestException,
                AuthenticatedRequest.UnauthenticatedRequestException,
                RepositoryException;
    }

}
