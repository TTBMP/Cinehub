package com.ttbmp.cinehub.app.usecase.getuserrole;

import com.ttbmp.cinehub.app.service.security.Role;

public interface GetUserRolePresenter {

    void present(Role[] roles);

}
