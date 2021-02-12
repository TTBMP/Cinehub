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
    private final ManageEmployeeShiftViewModel viewModel = UseCase.getViewModel();
    private Map<EmployeeDto, List<ShiftDto>> employeeShiftMap= new HashMap<>();
    private EmployeeDto selectedEmployee;
    private boolean projection;
    private ShiftDto selectedShift;
    private List<EmployeeDto> employeeComboBox;
    private HallDto hall;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (text != null)
                    setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
        });
    }

    @GetMapping("/manage_employee_shift")
    public String populateCinema(Model model) {
        useCase.getCinemaList();
        model.addAttribute("date", LocalDate.now());
        model.addAttribute("cinemaList", viewModel.getCinemaDtoList());
        model.addAttribute("cinemaSelected", false);
        GetShiftListRequest request = new GetShiftListRequest(LocalDate.now(), viewModel.getCinemaDtoList().get(0).getId());
        model.addAttribute("getShiftListRequest", request);
        return "/manage_employee_shift";

    }

    @PostMapping("/manage_employee_shift")
    public String loadShift(@ModelAttribute("getShiftListRequest") GetShiftListRequest request, Model model) {
        model.addAttribute("cinemaList", viewModel.getCinemaDtoList());
        viewModel.setSelectedCinema(request.getCinemaId());
        viewModel.setSelectedDate(request.getStart());
        model.addAttribute("cinemaSelected", true);
        useCase.getShiftList(request);
        employeeShiftMap = viewModel.getEmployeeShiftMap();
        model.addAttribute("shiftList", viewModel.getEmployeeShiftMap());
        return "/manage_employee_shift";
    }

    @GetMapping("/shift_detail/{indexEmployee}/{indexShift}")
    public String shiftDetail(
            @PathVariable int indexEmployee,
            @PathVariable int indexShift,
            Model model) {
        List<EmployeeDto> employeeDtoList =viewModel.getEmployeeDtoList();
        employeeShiftMap = viewModel.getEmployeeShiftMap();
        employeeDtoList.forEach(employeeDto ->{
            if (employeeDto.getId() == indexEmployee){
            selectedEmployee = employeeDto;
        }});
        viewModel.setShiftSelected(employeeShiftMap.get(selectedEmployee).get(indexShift));
        projection = viewModel.getShiftSelected() instanceof ShiftProjectionistDto;
        useCase.getHallList(new GetHallListRequest(viewModel.getSelectedCinema()));
        model.addAttribute("hallList", viewModel.getHallDtoList());
        model.addAttribute("projection", projection);
        model.addAttribute("shiftDetail", viewModel.getShiftSelected());
        model.addAttribute("employee", selectedEmployee);
        model.addAttribute("now", LocalDate.now());
        NewShiftRequest request = new NewShiftRequest();
        model.addAttribute("modifyRequest", request);
        return "/shift_detail";
    }

    @PostMapping("/shift_detail")
    public String modifyShift( @ModelAttribute("modifyRequest") NewShiftRequest request,
                            Model model) {
        model.addAttribute("hallList", viewModel.getHallDtoList());
        model.addAttribute("projection", projection);
        model.addAttribute("shiftDetail", selectedShift);
        model.addAttribute("employee", selectedEmployee);
        model.addAttribute("now", LocalDate.now());

        viewModel.getHallDtoList().forEach(hallDto -> {
            if(hallDto.getId() == request.hallId){
                hall = hallDto;
            }
        });
        useCase.createShift(new CreateShiftRequest(selectedEmployee, request.date, request.start, request.end, hall));
        useCase.modifyShift(new ShiftModifyRequest(viewModel.getShiftSelected(), viewModel.getShiftCreated()));
        return "/shift_modify";
    }


    @GetMapping("/assign_projectionist_shift")
    public String assignProjectionistShift(Model model) {
        useCase.getHallList(new GetHallListRequest(viewModel.getSelectedCinema()));
        employeeComboBox = viewModel.getEmployeeDtoList().stream().filter(employeeDto -> employeeDto.getClass().equals(ProjectionistDto.class)).collect(Collectors.toList());
        model.addAttribute("employeeList", employeeComboBox);
        model.addAttribute("hallList", viewModel.getHallDtoList());
        model.addAttribute("now", LocalDate.now());
        NewShiftRequest request = new NewShiftRequest();
        model.addAttribute("assignRequest", request);
        return "/assign_projectionist_shift";
    }

    @PostMapping("/assign_projectionist_shift")
    public String assignProjShift( @ModelAttribute("assignRequest") NewShiftRequest request,
                               Model model) {
        useCase.getHallList(new GetHallListRequest(viewModel.getSelectedCinema()));
        employeeComboBox = viewModel.getEmployeeDtoList().stream().filter(employeeDto -> employeeDto.getClass().equals(ProjectionistDto.class)).collect(Collectors.toList());
        model.addAttribute("employeeList", employeeComboBox);
        model.addAttribute("hallList", viewModel.getHallDtoList());
        model.addAttribute("now", LocalDate.now());
        viewModel.getEmployeeDtoList().forEach(employeeDto -> {
            if(employeeDto.getId() == request.employeeId){
                selectedEmployee=employeeDto;
            }

        });

        viewModel.getHallDtoList().forEach(hallDto -> {
            if(hallDto.getId() == request.hallId){
                hall = hallDto;
            }
        });
        useCase.createShift(new CreateShiftRequest(selectedEmployee, request.date, request.start, request.end, hall));
        useCase.saveShift(new ShiftRequest(viewModel.getShiftCreated()));
        return "/shift_assigned";
    }

    @GetMapping("/assign_usher_shift")
    public String assignUsherShift(Model model) {
        employeeComboBox = viewModel.getEmployeeDtoList().stream().filter(employeeDto -> employeeDto.getClass().equals(UsherDto.class)).collect(Collectors.toList());
        model.addAttribute("employeeList", employeeComboBox);
        model.addAttribute("now", LocalDate.now());
        NewShiftRequest request = new NewShiftRequest();
        model.addAttribute("assignRequest", request);
        return "/assign_usher_shift";
    }

    @PostMapping("/assign_usher_shift")
    public String assignUshShift( @ModelAttribute("assignRequest") NewShiftRequest request,
                                   Model model) {
        employeeComboBox = viewModel.getEmployeeDtoList().stream().filter(employeeDto -> employeeDto.getClass().equals(UsherDto.class)).collect(Collectors.toList());
        model.addAttribute("employeeList", employeeComboBox);
        viewModel.getEmployeeDtoList().forEach(employeeDto -> {
            if(employeeDto.getId() == request.employeeId){
                selectedEmployee=employeeDto;
            }

        });
        model.addAttribute("now", LocalDate.now());
        useCase.createShift(new CreateShiftRequest(selectedEmployee, request.date, request.start, request.end));
        useCase.saveShift(new ShiftRequest(viewModel.getShiftCreated()));
        return "/shift_assigned";
    }

    @GetMapping("/assign_repeated_projectionist_shift")
    public String assignRepeatedProjectionistShift(Model model) {
        useCase.getHallList(new GetHallListRequest(viewModel.getSelectedCinema()));
        employeeComboBox = viewModel.getEmployeeDtoList().stream().filter(employeeDto -> employeeDto.getClass().equals(ProjectionistDto.class)).collect(Collectors.toList());
        model.addAttribute("employeeList", employeeComboBox);
        model.addAttribute("hallList", viewModel.getHallDtoList());
        model.addAttribute("preferenceList", ShiftRepeatingOption.values());
        model.addAttribute("now", LocalDate.now());
        NewRepeatedShiftRequest request = new NewRepeatedShiftRequest();
        model.addAttribute("assignRequest", request);
        return "/assign_repeated_projectionist_shift";
    }

    @PostMapping("/assign_repeated_projectionist_shift")
    public String assignRepeatedProjShift( @ModelAttribute("assignRequest") NewRepeatedShiftRequest request,
                                  Model model) {
        useCase.getHallList(new GetHallListRequest(viewModel.getSelectedCinema()));
        employeeComboBox = viewModel.getEmployeeDtoList().stream().filter(employeeDto -> employeeDto.getClass().equals(ProjectionistDto.class)).collect(Collectors.toList());
        model.addAttribute("employeeList", employeeComboBox);
        model.addAttribute("hallList", viewModel.getHallDtoList());
        viewModel.getEmployeeDtoList().forEach(employeeDto -> {
            if(employeeDto.getId() == request.employeeId){
                selectedEmployee=employeeDto;
            }
        });
        viewModel.getHallDtoList().forEach(hallDto -> {
            if(hallDto.getId() == request.hallId){
                hall = hallDto;
            }
        });
        model.addAttribute("preferenceList", ShiftRepeatingOption.values());
        model.addAttribute("now", LocalDate.now());
        useCase.createShift(new CreateShiftRequest(selectedEmployee, request.date, request.start, request.end, hall));
        useCase.saveRepeatedShift(new ShiftRepeatRequest(request.date, request.dateRepeated, request.preference, viewModel.getShiftCreated(), hall));
        return "/shift_assigned";
    }

    @GetMapping("/assign_repeated_usher_shift")
    public String assignRepeatedUsherShift(Model model) {
        employeeComboBox = viewModel.getEmployeeDtoList().stream().filter(employeeDto -> employeeDto.getClass().equals(UsherDto.class)).collect(Collectors.toList());
        model.addAttribute("employeeList", employeeComboBox);
        model.addAttribute("now", LocalDate.now());
        model.addAttribute("preferenceList", ShiftRepeatingOption.values());
        NewRepeatedShiftRequest request = new NewRepeatedShiftRequest();
        model.addAttribute("assignRequest", request);
        return "/assign_repeated_usher_shift";
    }

    @PostMapping("/assign_repeated_usher_shift")
    public String assignRepeatedUshShift( @ModelAttribute("assignRequest") NewRepeatedShiftRequest request,
                                           Model model) {
        employeeComboBox = viewModel.getEmployeeDtoList().stream().filter(employeeDto -> employeeDto.getClass().equals(ProjectionistDto.class)).collect(Collectors.toList());
        model.addAttribute("employeeList", employeeComboBox);
        viewModel.getEmployeeDtoList().forEach(employeeDto -> {
            if(employeeDto.getId() == request.employeeId){
                selectedEmployee=employeeDto;
            }
        });
        model.addAttribute("preferenceList", ShiftRepeatingOption.values());
        model.addAttribute("now", LocalDate.now());
        useCase.createShift(new CreateShiftRequest(selectedEmployee, request.date, request.start, request.end));
        useCase.saveRepeatedShift(new ShiftRepeatRequest(request.date, request.dateRepeated, request.preference, viewModel.getShiftCreated(), null));
        return "/shift_assigned";
    }

    @GetMapping("/delete_shift")
    public String deleteShift(Model model) {
        useCase.deleteShift(new ShiftRequest(viewModel.getShiftSelected()));
        return "/delete_shift";
    }

}