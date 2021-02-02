package com.ttbmp.cinehub.app.client.desktop.ui.manageshift;


import com.ttbmp.cinehub.core.dto.*;

import com.ttbmp.cinehub.core.usecase.manageemployeesshift.ManageEmployeesShiftUseCase;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.request.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ManageShiftActivityTest {

    @Test
    void test_SaveShift_correct() throws IOException {
        ManageShiftActivity manageShiftActivity = new ManageShiftActivity();
        ManageEmployeesShiftUseCase useCase = manageShiftActivity.getUseCase(ManageEmployeesShiftUseCase.class);
        ManageEmployeesShiftViewModel viewModel = manageShiftActivity.getViewModel(ManageEmployeesShiftViewModel.class);

        boolean result = useCase.saveShift(new ShiftRequest(new ShiftDto(new UsherDto("fabio", "buracchi",  new CinemaDto("MultiPlex")),
                LocalDate.now(),
                LocalTime.now(),
                LocalTime.now().plusHours(2)
                )));
        assertEquals(result, false);
        boolean result2 = useCase.saveShift(new ShiftRequest(new ShiftDto(new UsherDto("fabio", "buracchi",  new CinemaDto("MultiPlex")),
                LocalDate.now().plusWeeks(1),
                LocalTime.now(),
                LocalTime.now().plusHours(2)
        )));
        assertEquals(result2, true);

    }

    @Test
    void test_DeleteShift_correct() throws IOException {
        ManageShiftActivity manageShiftActivity = new ManageShiftActivity();
        ManageEmployeesShiftUseCase useCase = manageShiftActivity.getUseCase(ManageEmployeesShiftUseCase.class);
        ManageEmployeesShiftViewModel viewModel = manageShiftActivity.getViewModel(ManageEmployeesShiftViewModel.class);

        viewModel.setSelectedWeek(LocalDate.now());
        useCase.getShiftList(new GetShiftListRequest(LocalDate.now(),new CinemaDto("MultiPlex")));

        useCase.deleteShift(new ShiftRequest(new ShiftDto(new UsherDto("fabio", "buracchi",  new CinemaDto("MultiPlex"))
                , LocalDate.now()
                , LocalTime.now(),
                LocalTime.now().plusHours(2))));
        assertEquals(viewModel.getEmployeeShiftWeekList().get(1).getWeekMap().size(), 7);

        useCase.deleteShift(new ShiftRequest(new ShiftDto(new UsherDto("fabio", "buracchi",  new CinemaDto("MultiPlex"))
                , LocalDate.now().plusDays(1)
                , LocalTime.now(),
                LocalTime.now().plusHours(2))));

        assertEquals(viewModel.getEmployeeShiftWeekList()
                .get(1).getWeekMap()
                .get(LocalDate.now().plusDays(1).getDayOfWeek()).getShiftList().size(), 0);
    }

    @Test
    void test_createShift_correct() throws IOException {
        ManageShiftActivity manageShiftActivity = new ManageShiftActivity();
        ManageEmployeesShiftUseCase useCase = manageShiftActivity.getUseCase(ManageEmployeesShiftUseCase.class);
        ManageEmployeesShiftViewModel viewModel = manageShiftActivity.getViewModel(ManageEmployeesShiftViewModel.class);

        UsherDto usher = new UsherDto("fabio", "buracchi",  new CinemaDto("MultiPlex"));
        ShiftDto shiftDto = new ShiftDto(usher, LocalDate.now() , LocalTime.now().minusHours(1), LocalTime.now());

        useCase.createShift(new CreateShiftRequest(usher, LocalDate.now()
                , LocalTime.now().minusHours(1),
                LocalTime.now()));

        assertEquals(viewModel.getShiftCreated().getStart(), shiftDto.getStart());
    }

    @Test
    void test_getShiftList_correct() throws IOException {
        ManageShiftActivity manageShiftActivity = new ManageShiftActivity();
        ManageEmployeesShiftUseCase useCase = manageShiftActivity.getUseCase(ManageEmployeesShiftUseCase.class);
        ManageEmployeesShiftViewModel viewModel = manageShiftActivity.getViewModel(ManageEmployeesShiftViewModel.class);

        viewModel.setSelectedWeek(LocalDate.now());
        useCase.getShiftList(new GetShiftListRequest(LocalDate.now(), new CinemaDto("Comunale")));

        assertEquals(viewModel.getEmployeeShiftWeekList().size(), 2);
    }

    @Test
    void test_saveRepeatedShift_correct() throws IOException {
        ManageShiftActivity manageShiftActivity = new ManageShiftActivity();
        ManageEmployeesShiftUseCase useCase = manageShiftActivity.getUseCase(ManageEmployeesShiftUseCase.class);
        ManageEmployeesShiftViewModel viewModel = manageShiftActivity.getViewModel(ManageEmployeesShiftViewModel.class);

        viewModel.setSelectedWeek(LocalDate.now());
        useCase.getShiftList(new GetShiftListRequest(LocalDate.now(),new CinemaDto("MultiPlex")));

        ShiftDto shiftDto = new ShiftUsherDto(new UsherDto("fabio", "buracchi",  new CinemaDto("MultiPlex")),
                LocalDate.now(),
                LocalTime.now(),
                LocalTime.now().plusMinutes(5));

        useCase.saveRepeatedShift(new ShiftRepeatRequest(LocalDate.now(), LocalDate.now().plusWeeks(2), "EVERY_DAY" , shiftDto));
        assertEquals(viewModel.getEmployeeShiftWeekList().size(), 2);

    }

    @Test
    void test_getCinemaList_correct() throws IOException {
        ManageShiftActivity manageShiftActivity = new ManageShiftActivity();
        ManageEmployeesShiftUseCase useCase = manageShiftActivity.getUseCase(ManageEmployeesShiftUseCase.class);
        ManageEmployeesShiftViewModel viewModel = manageShiftActivity.getViewModel(ManageEmployeesShiftViewModel.class);

        useCase.getCinemaList();
        assertEquals(viewModel.getCinemaList().size(), 2);

    }

    @Test
    void test_getHallList_correct() throws IOException {
        ManageShiftActivity manageShiftActivity = new ManageShiftActivity();
        ManageEmployeesShiftUseCase useCase = manageShiftActivity.getUseCase(ManageEmployeesShiftUseCase.class);
        ManageEmployeesShiftViewModel viewModel = manageShiftActivity.getViewModel(ManageEmployeesShiftViewModel.class);

        useCase.getHallList(new GetHallListRequest(new CinemaDto("Comunale")));
        assertEquals(viewModel.getHallList().size(), 2);

    }
}