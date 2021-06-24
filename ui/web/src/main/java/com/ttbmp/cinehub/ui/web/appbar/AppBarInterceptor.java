package com.ttbmp.cinehub.ui.web.appbar;

import com.ttbmp.cinehub.app.usecase.getuserrole.GetUserRoleHandler;
import com.ttbmp.cinehub.app.usecase.getuserrole.RoleRequest;
import com.ttbmp.cinehub.ui.web.utilities.ErrorHelper;
import com.ttbmp.cinehub.ui.web.utilities.ModelMapWrapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Component
public class AppBarInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler,
            ModelAndView modelAndView) {
        if (modelAndView != null) {
            var useCase = new GetUserRoleHandler(new AppBarPresenterWeb(new ModelMapWrapper(modelAndView.getModelMap())));
            var cookies = request.getCookies() == null ? new Cookie[]{} : request.getCookies();
            useCase.getUserRoles(new RoleRequest(
                    Arrays.stream(cookies)
                            .filter(cookie -> cookie.getName().equals("session"))
                            .map(Cookie::getValue)
                            .findAny()
                            .orElse(null)
            ));
            var viewName = modelAndView.getViewName();
            if (viewName != null) {
                viewName = viewName.split("/")[0];
                modelAndView.addObject("is_buy_ticket_tab_active", viewName.equals("buy_ticket"));
                modelAndView.addObject("is_manage_shift_tab_active", viewName.equals("manage_employee_shift"));
                modelAndView.addObject("is_view_personal_schedule_tab_active", viewName.equals("view_personal_schedule"));
                modelAndView.addObject("is_about_tab_active", viewName.equals("about"));
            }
            if (modelAndView.getModel().containsKey(ErrorHelper.ERROR_ATTRIBUTE_NAME)) {
                modelAndView.setViewName(ErrorHelper.ERROR_VIEW_PATH);
            }
        }
    }

}
