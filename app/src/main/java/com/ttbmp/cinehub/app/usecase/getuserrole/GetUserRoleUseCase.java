package com.ttbmp.cinehub.app.usecase.getuserrole;

import com.ttbmp.cinehub.app.utilities.usecase.UseCase;

public interface GetUserRoleUseCase extends UseCase {

    void getUserRoles(RoleRequest roleRequest);
}
