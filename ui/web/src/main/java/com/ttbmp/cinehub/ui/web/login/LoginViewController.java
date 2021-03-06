package com.ttbmp.cinehub.ui.web.login;

import com.ttbmp.cinehub.app.usecase.login.LoginHandler;
import com.ttbmp.cinehub.app.usecase.login.LoginRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginViewController {

    @GetMapping("/login")
    public String login(Model model) {
        var form = new LoginForm();
        model.addAttribute("loginForm", form);
        return "login";
    }

    @PostMapping("/login")
    public void login(HttpServletResponse response, @ModelAttribute LoginForm form, Model model) {
        var useCase = new LoginHandler(new LoginPresenterWeb(model));
        useCase.login(new LoginRequest(form.email, form.password));
        var sessionToken = model.getAttribute("sessionToken");
        if (sessionToken != null) {
            var cookie = new Cookie("session", (String) sessionToken);
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            response.addCookie(cookie);
        }
        response.setHeader("Location", sessionToken != null ? "/" : "/login");
        response.setStatus(302);
    }

}
