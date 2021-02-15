package com.ttbmp.cinehub.ui.web.about;

import org.springframework.web.bind.annotation.GetMapping;

public class AboutController {

    @GetMapping("/about")
    public String showShiftList() {
        return "about";
    }

}
