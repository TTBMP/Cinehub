package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import com.ttbmp.cinehub.app.dto.*;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftHandler;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftUseCase;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.GetShiftListRequest;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.ShiftModifyRequest;
import com.ttbmp.cinehub.ui.web.manageemployeeshift.form.NewShiftForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class ShiftDetailViewController {

    private static final String ERROR = "error";

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text != null)
                    setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
        });
    }

    @GetMapping("/shift_detail")
    public String shiftDetail(@RequestParam("idCinema") int cinemaId,
                              @RequestParam("shiftId") int shiftId,
                              Model model) {

        ManageEmployeesShiftUseCase useCase = new ManageEmployeesShiftHandler(new ManageEmployeeShiftPresenterWeb(model));
        model.addAttribute("shiftId", shiftId);
        model.addAttribute("idCinema", cinemaId);
        useCase.getCinemaList();
        var selectedCinema = (CinemaDto) model.getAttribute("selectedCinema");
        useCase.getShiftList(new GetShiftListRequest(LocalDate.now(), selectedCinema));
        var selectedEmployee = (EmployeeDto) model.getAttribute("selectedEmployee");
        var selectedShift = (ShiftDto) model.getAttribute("selectedShift");
        var projection = selectedShift instanceof ShiftProjectionistDto;
        model.addAttribute("projection", projection);
        model.addAttribute("shiftDetail", selectedShift);
        model.addAttribute("employee", selectedEmployee);
        model.addAttribute("now", LocalDate.now().plusDays(1));
        var request = new NewShiftForm();
        model.addAttribute("modifyRequest", request);
        return "/shift_detail";
    }

    @PostMapping("/shift_detail")
    public String modifyShift(@RequestParam("idCinema") int cinemaId,
                              @RequestParam("shiftId") int shiftId,
                              @ModelAttribute("modifyRequest") NewShiftForm request,
                              Model model) {

        ManageEmployeesShiftUseCase useCase = new ManageEmployeesShiftHandler(new ManageEmployeeShiftPresenterWeb(model));
        model.addAttribute("shiftId", shiftId);
        model.addAttribute("idCinema", cinemaId);
        model.addAttribute("selectedHallId", request.getHallId());
        useCase.getCinemaList();
        var selectedCinema = (CinemaDto) model.getAttribute("selectedCinema");
        var selectedHall = (HallDto) model.getAttribute("selectedHall");
        useCase.getShiftList(new GetShiftListRequest(LocalDate.now(), selectedCinema));
        var selectedShift = (ShiftDto) model.getAttribute("selectedShift");
        var selectedEmployee = (EmployeeDto) model.getAttribute("selectedEmployee");
        var projection = selectedShift instanceof ShiftProjectionistDto;
        model.addAttribute("projection", projection);
        model.addAttribute("shiftDetail", selectedShift);
        model.addAttribute("employee", selectedEmployee);
        model.addAttribute("now", LocalDate.now().plusDays(1));
        useCase.modifyShift(new ShiftModifyRequest(
                selectedEmployee,
                shiftId,
                request.getDate(),
                request.getInizio(),
                request.getEnd(),
                selectedHall
        ));
        var error = (boolean) model.getAttribute(ERROR);
        if (!error) {
            return "shift_modify";
        }
        return "/shift_detail";
    }

}
