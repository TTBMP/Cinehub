package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ModifyShiftViewController {

    @GetMapping("/shift_modify")
    public String shiftModify(Model model) {
        return "/shift_modify";
    }
}
