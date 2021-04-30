package com.ttbmp.cinehub.ui.web.about;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Fabio Buracchi
 */
@Controller
public class AboutController {

    @GetMapping("/about")
    public String homePage() {
        return "about";
    }

}
