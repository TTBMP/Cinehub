package com.ttbmp.cinehub.ui.web.appbar;

import com.ttbmp.cinehub.app.usecase.getuserrole.GetUserRoleHandler;
import com.ttbmp.cinehub.app.usecase.getuserrole.RoleRequest;
import com.ttbmp.cinehub.ui.web.utilities.ErrorHelper;
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
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            var useCase = new GetUserRoleHandler(new AppBarPresenterWeb(modelAndView.getModel()));
            var cookies = request.getCookies() == null ? new Cookie[]{} : request.getCookies();
            useCase.getUserRoles(new RoleRequest(
                    Arrays.stream(cookies)
                            .filter(cookie -> cookie.getName().equals("session"))
                            .map(Cookie::getValue)
                            .findAny()
                            .orElse(null)
            ));
            if (modelAndView.getModel().containsKey(ErrorHelper.ERROR_ATTRIBUTE_NAME)) {
                modelAndView.setViewName(ErrorHelper.ERROR_VIEW_PATH);
            }
        }
    }

}
