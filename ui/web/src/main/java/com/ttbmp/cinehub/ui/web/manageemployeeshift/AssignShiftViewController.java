package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AssignShiftViewController {

    private static final String SHIFT_ASSIGNED = "/shift_assigned";

    @GetMapping(SHIFT_ASSIGNED)
    public String shiftAssigned(Model model) {
        return SHIFT_ASSIGNED;
    }

}
