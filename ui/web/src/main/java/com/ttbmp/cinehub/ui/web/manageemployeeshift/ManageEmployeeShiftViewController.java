package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import com.ttbmp.cinehub.app.dto.*;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftUseCase;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.*;
import com.ttbmp.cinehub.domain.shift.ShiftRepeatingOption;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ManageEmployeeShiftViewController {

    private static final ManageEmployeesShiftUseCase useCase = UseCase.manageEmployeeUseCase;
    private static final String HALL_LIST = "hallList";
    private static final String ERROR = "error";
    private static final String ERROR_TEXT = "errorText";
    private static final String EMPLOYEE_LIST = "employeeList";
    private static final String ASSIGN_REQUEST = "assignRequest";
    private static final String SHIFT_ASSIGNED = "/shift_assigned";
    private static final String PREFERENCE_LIST = "preferenceList";
    private final ManageEmployeeShiftViewModel viewModel = UseCase.getViewModel();
    private Map<EmployeeDto, List<ShiftDto>> employeeShiftMap = new HashMap<>();
    private EmployeeDto selectedEmployee;
    private boolean projection;
    private List<EmployeeDto> employeeComboBox;
    private HallDto hall;

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


    @GetMapping("/manage_employee_shift")
    public String populateCinema(Model model) {
        useCase.getCinemaList();
        model.addAttribute("cinemaList", viewModel.getCinemaDtoList());
        model.addAttribute("cinemaSelected", false);
        GetCinemaRequest request = new GetCinemaRequest();
        model.addAttribute("getShiftListRequest", request);
        return "/manage_employee_shift";

    }

    @PostMapping("/manage_employee_shift")
    public String loadShift(@ModelAttribute("getShiftListRequest") GetCinemaRequest request, Model model) {
        model.addAttribute("cinemaList", viewModel.getCinemaDtoList());
        viewModel.getCinemaDtoList().forEach(cinema -> {
            if (cinema.getId() == request.getCinemaId()) {
                viewModel.setSelectedCinema(cinema);
            }
        });

        viewModel.setSelectedDate(request.getStart());
        model.addAttribute("cinemaSelected", true);
        useCase.getShiftList(new GetShiftListRequest(request.getStart(), viewModel.getSelectedCinema()));
        employeeShiftMap = viewModel.getEmployeeShiftMap();
        model.addAttribute("shiftList", viewModel.getEmployeeShiftMap());
        return "/manage_employee_shift";
    }

    @GetMapping("/shift_detail/{indexEmployee}/{indexShift}")
    public String shiftDetail(
            @PathVariable String indexEmployee,
            @PathVariable int indexShift,
            Model model) {
        List<EmployeeDto> employeeDtoList = viewModel.getEmployeeDtoList();
        employeeShiftMap = viewModel.getEmployeeShiftMap();
        employeeDtoList.forEach(employeeDto -> {
            if (employeeDto.getId().equals(indexEmployee)) {
                selectedEmployee = employeeDto;
            }
        });
        viewModel.setShiftSelected(employeeShiftMap.get(selectedEmployee).get(indexShift));
        projection = viewModel.getShiftSelected() instanceof ShiftProjectionistDto;


        useCase.getHallList(new GetHallListRequest(viewModel.getSelectedCinema()));
        model.addAttribute(HALL_LIST, viewModel.getHallDtoList());
        model.addAttribute("projection", projection);
        model.addAttribute("shiftDetail", viewModel.getShiftSelected());
        model.addAttribute("employee", selectedEmployee);
        model.addAttribute("now", LocalDate.now().plusDays(1));
        NewShiftRequest request = new NewShiftRequest();
        model.addAttribute("modifyRequest", request);
        return "/shift_detail";
    }

    @PostMapping("/shift_detail")
    public String modifyShift(@ModelAttribute("modifyRequest") NewShiftRequest request,
                              Model model) {
        model.addAttribute(HALL_LIST, viewModel.getHallDtoList());
        model.addAttribute("projection", projection);
        model.addAttribute("shiftDetail", viewModel.getShiftSelected());
        model.addAttribute("employee", selectedEmployee);
        model.addAttribute("now", LocalDate.now().plusDays(1));

        viewModel.getHallDtoList().forEach(hallDto -> {
            if (hallDto.getId() == request.hallId) {
                hall = hallDto;
            }
        });
        useCase.modifyShift(new ShiftModifyRequest(
                viewModel.getShiftSelected().getEmployee(),
                viewModel.getShiftSelected().getId(),
                request.getDate(),
                request.getStart(),
                request.getEnd(),
                hall
        ));

        model.addAttribute(ERROR_TEXT, viewModel.getModifyErrorText());
        model.addAttribute(ERROR, viewModel.isModifyError());
        if (!viewModel.isModifyError()) {
            return "shift_modify";
        }
        return "/shift_detail";

    }

    @GetMapping("/assign_projectionist_shift")
    public String assignProjectionistShift(Model model) {
        useCase.getHallList(new GetHallListRequest(viewModel.getSelectedCinema()));
        employeeComboBox = viewModel.getEmployeeDtoList().stream().filter(employeeDto -> employeeDto.getClass().equals(ProjectionistDto.class)).collect(Collectors.toList());
        model.addAttribute(EMPLOYEE_LIST, employeeComboBox);
        model.addAttribute(HALL_LIST, viewModel.getHallDtoList());
        model.addAttribute("now", LocalDate.now().plusDays(1));
        NewShiftRequest request = new NewShiftRequest();
        model.addAttribute(ASSIGN_REQUEST, request);
        return "/assign_projectionist_shift";
    }

    @PostMapping("/assign_projectionist_shift")
    public String assignProjShift(@ModelAttribute(ASSIGN_REQUEST) NewShiftRequest request,
                                  Model model) {
        useCase.getHallList(new GetHallListRequest(viewModel.getSelectedCinema()));
        employeeComboBox = viewModel.getEmployeeDtoList().stream().filter(employeeDto -> employeeDto.getClass().equals(ProjectionistDto.class)).collect(Collectors.toList());
        model.addAttribute(EMPLOYEE_LIST, employeeComboBox);
        model.addAttribute(HALL_LIST, viewModel.getHallDtoList());
        model.addAttribute("now", LocalDate.now().plusDays(1));
        viewModel.getEmployeeDtoList().forEach(employeeDto -> {
            if (employeeDto.getId().equals(request.employeeId)) {
                selectedEmployee = employeeDto;
            }

        });

        viewModel.getHallDtoList().forEach(hallDto -> {
            if (hallDto.getId() == request.hallId) {
                hall = hallDto;
            }
        });
        useCase.createShift(new CreateShiftRequest(selectedEmployee, request.date, request.start, request.end, hall));
        model.addAttribute(ERROR_TEXT, viewModel.getAssignErrorText());
        model.addAttribute(ERROR, viewModel.isAssignError());
        if (!viewModel.isAssignError()) {
            return SHIFT_ASSIGNED;
        }
        return "/assign_projectionist_shift";
    }

    @GetMapping("/assign_usher_shift")
    public String assignUsherShift(Model model) {
        employeeComboBox = viewModel.getEmployeeDtoList().stream().filter(employeeDto -> employeeDto.getClass().equals(UsherDto.class)).collect(Collectors.toList());
        model.addAttribute(EMPLOYEE_LIST, employeeComboBox);
        model.addAttribute("now", LocalDate.now().plusDays(1));
        NewShiftRequest request = new NewShiftRequest();
        model.addAttribute(ASSIGN_REQUEST, request);
        return "/assign_usher_shift";
    }

    @PostMapping("/assign_usher_shift")
    public String assignUshShift(@ModelAttribute(ASSIGN_REQUEST) NewShiftRequest request,
                                 Model model) {
        employeeComboBox = viewModel.getEmployeeDtoList().stream().filter(employeeDto -> employeeDto.getClass().equals(UsherDto.class)).collect(Collectors.toList());
        model.addAttribute(EMPLOYEE_LIST, employeeComboBox);
        viewModel.getEmployeeDtoList().forEach(employeeDto -> {
            if (employeeDto.getId().equals(request.employeeId)) {
                selectedEmployee = employeeDto;
            }

        });
        model.addAttribute("now", LocalDate.now().plusDays(1));
        useCase.createShift(new CreateShiftRequest(selectedEmployee, request.date, request.start, request.end));
        model.addAttribute(ERROR_TEXT, viewModel.getAssignErrorText());
        model.addAttribute(ERROR, viewModel.isAssignError());
        if (!viewModel.isAssignError()) {
            return SHIFT_ASSIGNED;
        }
        return "/assign_usher_shift";
    }

    @GetMapping("/assign_repeated_projectionist_shift")
    public String assignRepeatedProjectionistShift(Model model) {
        useCase.getHallList(new GetHallListRequest(viewModel.getSelectedCinema()));
        employeeComboBox = viewModel.getEmployeeDtoList().stream().filter(employeeDto -> employeeDto.getClass().equals(ProjectionistDto.class)).collect(Collectors.toList());
        model.addAttribute(EMPLOYEE_LIST, employeeComboBox);
        model.addAttribute(HALL_LIST, viewModel.getHallDtoList());
        model.addAttribute(PREFERENCE_LIST, ShiftRepeatingOption.values());
        model.addAttribute("now", LocalDate.now().plusDays(1));
        NewRepeatedShiftRequest request = new NewRepeatedShiftRequest();
        model.addAttribute(ASSIGN_REQUEST, request);
        return "/assign_repeated_projectionist_shift";
    }

    @PostMapping("/assign_repeated_projectionist_shift")
    public String assignRepeatedProjShift(@ModelAttribute(ASSIGN_REQUEST) NewRepeatedShiftRequest request,
                                          Model model) {
        useCase.getHallList(new GetHallListRequest(viewModel.getSelectedCinema()));
        employeeComboBox = viewModel.getEmployeeDtoList().stream().filter(employeeDto -> employeeDto.getClass().equals(ProjectionistDto.class)).collect(Collectors.toList());
        model.addAttribute(EMPLOYEE_LIST, employeeComboBox);
        model.addAttribute(HALL_LIST, viewModel.getHallDtoList());
        viewModel.getEmployeeDtoList().forEach(employeeDto -> {
            if (employeeDto.getId().equals(request.employeeId)) {
                selectedEmployee = employeeDto;
            }
        });
        viewModel.getHallDtoList().forEach(hallDto -> {
            if (hallDto.getId() == request.hallId) {
                hall = hallDto;
            }
        });
        model.addAttribute(PREFERENCE_LIST, ShiftRepeatingOption.values());
        model.addAttribute("now", LocalDate.now().plusDays(1));
        useCase.saveRepeatedShift(new ShiftRepeatRequest(
                request.date,
                request.dateRepeated,
                request.preference,
                selectedEmployee,
                request.getStart(),
                request.getEnd(),
                hall));
        model.addAttribute(ERROR_TEXT, viewModel.getAssignErrorText());
        model.addAttribute(ERROR, viewModel.isAssignError());
        if (!viewModel.isAssignError()) {
            return SHIFT_ASSIGNED;
        }
        return "/assign_repeated_projectionist_shift";
    }

    @GetMapping("/assign_repeated_usher_shift")
    public String assignRepeatedUsherShift(Model model) {
        employeeComboBox = viewModel.getEmployeeDtoList().stream().filter(employeeDto -> employeeDto.getClass().equals(UsherDto.class)).collect(Collectors.toList());
        model.addAttribute(EMPLOYEE_LIST, employeeComboBox);
        model.addAttribute("now", LocalDate.now().plusDays(1));
        model.addAttribute(PREFERENCE_LIST, ShiftRepeatingOption.values());
        NewRepeatedShiftRequest request = new NewRepeatedShiftRequest();
        model.addAttribute(ASSIGN_REQUEST, request);
        return "/assign_repeated_usher_shift";
    }

    @PostMapping("/assign_repeated_usher_shift")
    public String assignRepeatedUshShift(@ModelAttribute(ASSIGN_REQUEST) NewRepeatedShiftRequest request,
                                         Model model) {
        employeeComboBox = viewModel.getEmployeeDtoList().stream().filter(employeeDto -> employeeDto.getClass().equals(ProjectionistDto.class)).collect(Collectors.toList());
        model.addAttribute(EMPLOYEE_LIST, employeeComboBox);
        viewModel.getEmployeeDtoList().forEach(employeeDto -> {
            if (employeeDto.getId().equals(request.employeeId)) {
                selectedEmployee = employeeDto;
            }
        });
        model.addAttribute(PREFERENCE_LIST, ShiftRepeatingOption.values());
        model.addAttribute("now", LocalDate.now().plusDays(1));
        useCase.saveRepeatedShift(new ShiftRepeatRequest(
                request.date,
                request.dateRepeated,
                request.preference,
                selectedEmployee,
                request.getStart(),
                request.getEnd(),
                hall));
        model.addAttribute(ERROR_TEXT, viewModel.getAssignErrorText());
        model.addAttribute(ERROR, viewModel.isAssignError());
        if (!viewModel.isAssignError()) {
            return SHIFT_ASSIGNED;
        }
        return "/assign_repeated_usher_shift";
    }

    @GetMapping("/delete_shift")
    public String deleteShift(Model model) {
        useCase.deleteShift(new ShiftRequest(viewModel.getShiftSelected()));
        model.addAttribute(ERROR, viewModel.isDeleteError());
        model.addAttribute(ERROR_TEXT, viewModel.getDeleteErrorText());
        return "/delete_shift";
    }

    @GetMapping(SHIFT_ASSIGNED)
    public String shiftAssigned(Model model) {
        model.addAttribute(ERROR, viewModel.isAssignError());
        model.addAttribute(ERROR_TEXT, viewModel.getAssignErrorText());
        return SHIFT_ASSIGNED;
    }

    @GetMapping("/shift_modify")
    public String shiftModify(Model model) {
        model.addAttribute("newShift", viewModel.getShiftCreated());
        return "/shift_modify";
    }

}