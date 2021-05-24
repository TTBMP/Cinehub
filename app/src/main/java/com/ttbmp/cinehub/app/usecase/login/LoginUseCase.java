package com.ttbmp.cinehub.app.usecase.login;

import com.ttbmp.cinehub.app.usecase.UseCase;

public interface LoginUseCase extends UseCase {

    void login(LoginRequest loginRequest);

}
