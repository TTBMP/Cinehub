package com.ttbmp.cinehub.app.usecase.getuserrole;

import com.ttbmp.cinehub.app.service.security.SecurePresenter;

public interface GetUserRolePresenter extends SecurePresenter {

    void present(RoleReply reply);

}
