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
    public String Login(Model model) {
        var form = new LoginForm();
        model.addAttribute("loginForm", form);
        return "login";
    }

    @PostMapping("/login")
    public void Login(HttpServletResponse response, @ModelAttribute LoginForm form, Model model) {
        var useCase = new LoginHandler(new LoginPresenterWeb(model));
        useCase.login(new LoginRequest(form.email, form.password));
        var sessionToken = model.getAttribute("sessionToken");
        if (sessionToken != null) {
            response.addCookie(new Cookie("session", (String) sessionToken));
        }
        response.setHeader("Location", sessionToken != null ? "/" : "/login");
        response.setStatus(302);
    }

}
