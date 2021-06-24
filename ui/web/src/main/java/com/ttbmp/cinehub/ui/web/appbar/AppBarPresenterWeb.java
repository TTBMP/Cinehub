package com.ttbmp.cinehub.ui.web.appbar;

import com.ttbmp.cinehub.app.usecase.getuserrole.GetUserRolePresenter;
import com.ttbmp.cinehub.app.usecase.getuserrole.RoleReply;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.ui.web.utilities.PresenterWeb;
import org.springframework.ui.Model;

import static com.ttbmp.cinehub.app.usecase.getuserrole.RoleReply.Role.EMPLOYEE;
import static com.ttbmp.cinehub.app.usecase.getuserrole.RoleReply.Role.MANAGER;

public class AppBarPresenterWeb extends PresenterWeb implements GetUserRolePresenter {

    public AppBarPresenterWeb(Model model) {
        super(model);
    }

    @Override
    public void present(RoleReply reply) {
        model.addAttribute("is_about_tab_visible", true);
        model.addAttribute("is_logout_tab_visible", true);
        model.addAttribute("is_buy_ticket_tab_visible", true);
        model.addAttribute("is_view_personal_schedule_tab_visible", reply.getRoleList().contains(EMPLOYEE));
        model.addAttribute("is_manage_shift_tab_visible", reply.getRoleList().contains(MANAGER));
    }

    @Override
    public void presentUnauthenticatedError(AuthenticatedRequest.UnauthenticatedRequestException e) {
        model.addAttribute("is_about_tab_visible", true);
        model.addAttribute("is_login_tab_visible", true);
        model.addAttribute("is_buy_ticket_tab_visible", true);
    }

}
