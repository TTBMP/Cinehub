package com.ttbmp.cinehub.core.usecase.manageemployeesshift;

import com.ttbmp.cinehub.core.datamapper.CinemaDataMapper;
import com.ttbmp.cinehub.core.datamapper.EmployeeDataMapper;
import com.ttbmp.cinehub.core.datamapper.HallDataMapper;
import com.ttbmp.cinehub.core.datamapper.ShiftDataMapper;
import com.ttbmp.cinehub.core.dto.ShiftDto;
import com.ttbmp.cinehub.core.dto.UsherDto;
import com.ttbmp.cinehub.core.entity.Employee;
import com.ttbmp.cinehub.core.entity.Shift;
import com.ttbmp.cinehub.core.repository.CinemaRepository;
import com.ttbmp.cinehub.core.repository.HallRepository;
import com.ttbmp.cinehub.core.repository.ShiftRepository;
import com.ttbmp.cinehub.core.usecase.Request;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.request.*;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.response.*;
import com.ttbmp.cinehub.core.utilities.result.Result;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Massimo Mazzetti
 */

public class ManageEmployeesShiftController implements ManageEmployeesShiftUseCase {
    private final ManageEmployeesShiftPresenter manageEmployeesShiftPresenter;
    private final ShiftRepository shiftRepository;
    private final CinemaRepository cinemaRepository;
    private final HallRepository hallRepository;

    public ManageEmployeesShiftController(ManageEmployeesShiftPresenter manageEmployeesShiftPresenter, ShiftRepository shiftRepository, CinemaRepository cinemaRepository, HallRepository hallRepository) {
        this.manageEmployeesShiftPresenter = manageEmployeesShiftPresenter;
        this.shiftRepository = shiftRepository;
        this.cinemaRepository = cinemaRepository;
        this.hallRepository = hallRepository;
    }

    @Override
    public void getShiftList(GetShiftListRequest request) {
        manageEmployeesShiftPresenter.presentShiftList(new Result<>(new GetShiftListResponse(ShiftDataMapper.mapToDtoList(shiftRepository.getShiftList().getValue()), request.getStart(), request.getCinema())));
    }

    @Override
    public boolean saveShift(ShiftRequest request) {
        try {
            Request.validate(request);
        } catch (Request.NullRequestException | Request.InvalidRequestException e) {
            e.printStackTrace();
        }
        Result<Boolean> shiftResult = shiftRepository.saveShift(ShiftDataMapper.mapToEntity(request.getShift()));
        boolean result = shiftResult.getValue();
        if (result) {
            manageEmployeesShiftPresenter.presentSaveShift(new Result<>(new ShiftResponse(request.getShift())));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void deleteShift(ShiftRequest request) {
        try {
            Request.validate(request);
        } catch (Request.NullRequestException | Request.InvalidRequestException e) {
            e.printStackTrace();
        }
        Result<Shift> shiftResult = shiftRepository.deletedShift(ShiftDataMapper.mapToEntity(request.getShift()));
        manageEmployeesShiftPresenter.presentDeleteShift(new Result<>(new ShiftResponse(ShiftDataMapper.mapToDto(shiftResult.getValue()))));
    }

    @Override
    public void getCinemaList() {
        manageEmployeesShiftPresenter.presentCinemaList(new Result<>(new GetCinemaListResponse(CinemaDataMapper.mapToDtoList(cinemaRepository.getAllCinema()))));
    }

    @Override
    public void getHallList(GetHallListRequest request) {
        manageEmployeesShiftPresenter.presentHallList(new Result<>(new GetHallListResponse(HallDataMapper.mapToDtoList(hallRepository.getCinemaHallList(CinemaDataMapper.matToEntity(request.getCinema()))))));
    }

    @Override
    public void saveRepeatedShift(ShiftRepeatRequest request) {
        List<ShiftDto> shiftDtoList = new ArrayList<>();
        switch (request.getOption()) {
            case "EVERY_DAY":
                for (LocalDate date = request.getStart(); date.isBefore(request.getEnd().plusDays(1)); date = date.plusDays(1)) {
                    createShift(request, date, shiftDtoList);
                }
                manageEmployeesShiftPresenter.presentRepeatShift(new Result<>(new ShiftRepeatResponse(shiftDtoList)));
                break;
            case "EVERY_WEEK":
                for (LocalDate date = request.getStart(); date.isBefore(request.getEnd().plusDays(1)); date = date.plusWeeks(1)) {
                    createShift(request, date, shiftDtoList);

                }
                manageEmployeesShiftPresenter.presentRepeatShift(new Result<>(new ShiftRepeatResponse(shiftDtoList)));
                break;
            case "EVERY_MONTH":
                for (LocalDate date = request.getStart(); date.isBefore(request.getEnd().plusDays(1)); date = date.plusMonths(1)) {
                    createShift(request, date, shiftDtoList);
                }
                manageEmployeesShiftPresenter.presentRepeatShift(new Result<>(new ShiftRepeatResponse(shiftDtoList)));
                break;
            default:
                break;
        }

    }

    @Override
    public void createShift(CreateShiftRequest request) {

        try {
            Request.validate(request);
        } catch (Request.NullRequestException | Request.InvalidRequestException e) {
            e.printStackTrace();
        }
        Employee employee = EmployeeDataMapper.matToEntity(request.getEmployee());
        String date = request.getDate().toString();
        String start = request.getStart().toString();
        String end = request.getEnd().toString();
        Shift shift = employee.createShift(date, start, end, HallDataMapper.matToEntity(request.getHall()));
        manageEmployeesShiftPresenter.presentCreateShift(new Result<>(new CreateShiftResponse(ShiftDataMapper.mapToDto(shift))));
    }



    public void createShift(ShiftRepeatRequest request, LocalDate date, List<ShiftDto> shiftDtoList) {
        ShiftDto shiftDto;

        if (request.getShift().getEmployee() instanceof UsherDto) {
            shiftDto = new ShiftDto(request.getShift().getEmployee(),
                    date,
                    request.getShift().getStart(),
                    request.getShift().getEnd());
        } else {
            shiftDto = new ShiftDto(request.getShift().getEmployee(),
                    date,
                    request.getShift().getStart(),
                    request.getShift().getEnd(),
                    request.getShift().getHall());
        }
        Result<Boolean> result = shiftRepository.saveShift(ShiftDataMapper.mapToEntity(shiftDto));
        if (result.getValue().equals(true)) {
            shiftDtoList.add(shiftDto);
        }
    }


}
