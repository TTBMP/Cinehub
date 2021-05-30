package com.ttbmp.cinehub.ui.web.logout;

import com.ttbmp.cinehub.app.usecase.logout.LogoutHandler;
import com.ttbmp.cinehub.app.usecase.logout.LogoutRequest;
import com.ttbmp.cinehub.ui.web.utilities.ErrorHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LogoutViewController {

    @GetMapping("/logout")
    public void login(
            HttpServletResponse response,
            @CookieValue(value = "session", required = false) String sessionToken,
            Model model) {
        String location;
        var useCase = new LogoutHandler(new LogoutPresenterWeb(model));
        useCase.logout(new LogoutRequest(sessionToken));
        if (model.getAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME) != null) {
            location = ErrorHelper.ERROR_VIEW_PATH;
        } else {
            if (sessionToken != null) {
                var cookie = new Cookie("session", null);
                cookie.setHttpOnly(true);
                cookie.setSecure(true);
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
            response.setHeader("Location", "/");
            location = "/";
        }
        response.setHeader("Location", location);
        response.setStatus(302);
    }

}
