package com.ttbmp.cinehub.app.usecase.logout;

import com.ttbmp.cinehub.app.utilities.usecase.UseCase;

public interface LogoutUseCase extends UseCase {

    void logout(LogoutRequest logoutRequest);

}
