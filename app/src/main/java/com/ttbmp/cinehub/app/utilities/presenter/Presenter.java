package com.ttbmp.cinehub.app.utilities.presenter;

import com.ttbmp.cinehub.app.CinehubException;
import com.ttbmp.cinehub.app.utilities.request.Request;

public interface Presenter {

    void presentNullRequest();

    void presentInvalidRequest(Request request);

    void presentApplicationError(CinehubException e);

}
