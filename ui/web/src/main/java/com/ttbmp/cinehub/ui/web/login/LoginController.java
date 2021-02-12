package com.ttbmp.cinehub.ui.web.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String Login(Model model) {
        return "login";
    }

}
