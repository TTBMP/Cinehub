package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.dto.EmployeeDto;
import com.ttbmp.cinehub.app.dto.HallDto;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftHandler;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftUseCase;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.CreateShiftRequest;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.GetHallListRequest;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.GetShiftListRequest;
import com.ttbmp.cinehub.ui.web.manageemployeeshift.form.NewShiftForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class AssignProjectionistShiftViewController {

    private static final String ASSIGN_REQUEST = "assignRequest";
    private static final String SHIFT_ASSIGNED = "/shift_assigned";
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

    @GetMapping("/assign_projectionist_shift")
    public String assignProjectionistShift(@RequestParam(value = "idCinema") int cinemaId, Model model) {

        ManageEmployeesShiftUseCase useCase = new ManageEmployeesShiftHandler(new ManageEmployeeShiftPresenterWeb(model));

        model.addAttribute("idCinema", cinemaId);

        useCase.getCinemaList();

        CinemaDto selectedCinema = (CinemaDto) model.getAttribute("selectedCinema");

        useCase.getHallList(new GetHallListRequest(selectedCinema));
        useCase.getShiftList(new GetShiftListRequest(LocalDate.now(), selectedCinema));

        model.addAttribute("now", LocalDate.now().plusDays(1));

        NewShiftForm shiftRequest = new NewShiftForm();
        model.addAttribute(ASSIGN_REQUEST, shiftRequest);
        return "/assign_projectionist_shift";
    }

    @PostMapping("/assign_projectionist_shift")
    public String assignProjShift(@RequestParam(value = "idCinema") int cinemaId,
                                  @ModelAttribute(ASSIGN_REQUEST) NewShiftForm shiftRequest,
                                  Model model) {

        ManageEmployeesShiftUseCase useCase = new ManageEmployeesShiftHandler(new ManageEmployeeShiftPresenterWeb(model));
        model.addAttribute("idCinema", cinemaId);
        model.addAttribute("selectedEmployeeId", shiftRequest.getEmployeeId());
        model.addAttribute("selectedHallId", shiftRequest.getHallId());

        useCase.getCinemaList();

        CinemaDto selectedCinema = (CinemaDto) model.getAttribute("selectedCinema");

        useCase.getHallList(new GetHallListRequest(selectedCinema));
        useCase.getShiftList(new GetShiftListRequest(LocalDate.now(), selectedCinema));

        model.addAttribute("now", LocalDate.now().plusDays(1));

        EmployeeDto selectedEmployee = (EmployeeDto) model.getAttribute("selectedEmployee");
        HallDto selectedHall = (HallDto) model.getAttribute("selectedHall");

        useCase.createShift(new CreateShiftRequest(selectedEmployee, shiftRequest.getDate(), shiftRequest.getInizio(), shiftRequest.getEnd(), selectedHall));
        boolean error = (boolean) model.getAttribute(ERROR);
        if (!error) {
            return SHIFT_ASSIGNED;
        }
        return "/assign_projectionist_shift";

    }

}
