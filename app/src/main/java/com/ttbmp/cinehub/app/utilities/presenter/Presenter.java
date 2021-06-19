package com.ttbmp.cinehub.app.utilities.presenter;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.utilities.request.Request;

public interface Presenter {

    void presentNullRequest();

    void presentInvalidRequest(Request request);

    void presentRepositoryError(RepositoryException e);

}
