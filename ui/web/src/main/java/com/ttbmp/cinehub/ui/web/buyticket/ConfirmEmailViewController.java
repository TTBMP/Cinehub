package com.ttbmp.cinehub.ui.web.buyticket;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Palmieri Ivan
 */
@Controller
public class ConfirmEmailViewController {
    @GetMapping("/confirm_email")
    public String confirmEmail(Model model) {
        return "confirm_email";
    }

}
