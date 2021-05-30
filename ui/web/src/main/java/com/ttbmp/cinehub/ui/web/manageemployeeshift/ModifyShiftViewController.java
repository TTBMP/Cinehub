package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ModifyShiftViewController {

    @PostMapping("/shift_modify")
    public String shiftModify(Model model) {
        return "manage_employee_shift/notification";
    }

}
