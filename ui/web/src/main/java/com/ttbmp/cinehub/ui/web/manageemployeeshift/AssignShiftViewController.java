package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AssignShiftViewController {

    @GetMapping("/shift_assigned")
    public String shiftAssigned(Model model) {
        return "manage_employee_shift/shift_assigned";
    }

}
