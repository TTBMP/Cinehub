package com.ttbmp.cinehub.core.usecase.manageemployeesshift;

import com.ttbmp.cinehub.core.ShiftSaveException;
import com.ttbmp.cinehub.core.datamapper.CinemaDataMapper;
import com.ttbmp.cinehub.core.datamapper.EmployeeDataMapper;
import com.ttbmp.cinehub.core.datamapper.HallDataMapper;
import com.ttbmp.cinehub.core.datamapper.ShiftDataMapper;
import com.ttbmp.cinehub.core.dto.ShiftDto;
import com.ttbmp.cinehub.core.dto.ShiftProjectionistDto;
import com.ttbmp.cinehub.core.dto.UsherDto;
import com.ttbmp.cinehub.core.entity.Employee;
import com.ttbmp.cinehub.core.entity.shift.Shift;
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
    public void getCinemaList() {
        manageEmployeesShiftPresenter.presentCinemaList(new Result<>(new GetCinemaListResponse(
                CinemaDataMapper.mapToDtoList(cinemaRepository.getAllCinema())
        )));
    }

    @Override
    public void getHallList(GetHallListRequest request) {
        try {
            Request.validate(request);
            manageEmployeesShiftPresenter.presentHallList(new Result<>(new GetHallListResponse(
                    HallDataMapper.mapToDtoList(hallRepository.getCinemaHallList(
                            CinemaDataMapper.mapToEntity(request.getCinema())
                    ))
            )));
        } catch (Request.NullRequestException e) {
            manageEmployeesShiftPresenter.presentHallListNullRequest();
        } catch (Request.InvalidRequestException e) {
            manageEmployeesShiftPresenter.presentInvalidHallListRequest(request);
        }
    }

    @Override
    public void getShiftList(GetShiftListRequest request) {
        try {
            Request.validate(request);
            manageEmployeesShiftPresenter.presentShiftList(new Result<>(new GetShiftListResponse(
                    ShiftDataMapper.mapToDtoList(shiftRepository.getShiftList().getValue()),
                    request.getStart(),
                    request.getCinema())
            ));
        } catch (Request.NullRequestException e) {
            manageEmployeesShiftPresenter.presentGetShiftListNullRequest();
        } catch (Request.InvalidRequestException e) {
            manageEmployeesShiftPresenter.presentInvalidGetShiftListRequest(request);
        }
    }

    @Override
    public void saveShift(ShiftRequest request) {
        try {
            Request.validate(request);
            shiftRepository.saveShift(ShiftDataMapper.mapToEntity(request.getShift()));
            manageEmployeesShiftPresenter.presentSaveShift();
        } catch (Request.NullRequestException e) {
            manageEmployeesShiftPresenter.presentSaveShiftNullRequest();
        } catch (Request.InvalidRequestException e) {
            manageEmployeesShiftPresenter.presentInvalidSaveShiftListRequest(request);
        } catch (ShiftSaveException e) {
            manageEmployeesShiftPresenter.presentSaveShiftError(e);
        }
    }

    @Override
    public void modifyShift(ShiftModifyRequest request) {
        try {
            Request.validate(request);
            shiftRepository.modifyShift(ShiftDataMapper.mapToEntity(request.getOldShift()),ShiftDataMapper.mapToEntity(request.getNewShift()));
            manageEmployeesShiftPresenter.presentSaveShift();
            manageEmployeesShiftPresenter.presentDeleteShift();
        } catch (Request.NullRequestException e) {
           manageEmployeesShiftPresenter.presentModifyShiftNullRequest();
        } catch (Request.InvalidRequestException e) {
            manageEmployeesShiftPresenter.presentInvalidModifyShiftListRequest(request);
        } catch (ShiftSaveException e) {
            manageEmployeesShiftPresenter.presentModifyShiftError(e); ;
        }

    }

    @Override
    public void deleteShift(ShiftRequest request) {
        try {
            Request.validate(request);
            shiftRepository.deletedShift(ShiftDataMapper.mapToEntity(request.getShift()));
            manageEmployeesShiftPresenter.presentDeleteShift();
        } catch (Request.NullRequestException e) {
            manageEmployeesShiftPresenter.presentDeleteShiftNullRequest();
        } catch (Request.InvalidRequestException e) {
            manageEmployeesShiftPresenter.presentInvalidDeleteShiftListRequest(request);
        } catch (ShiftSaveException e) {
            manageEmployeesShiftPresenter.presentDeleteShiftError(e);
        }
    }

    @Override
    public void saveRepeatedShift(ShiftRepeatRequest request) {
        try {
            Request.validate(request);
            List<ShiftDto> shiftDtoList = new ArrayList<>();
            switch (request.getOption()) {
                case "EVERY_DAY":
                    for (LocalDate date = request.getStart(); date.isBefore(request.getEnd().plusDays(1)); date = date.plusDays(1)) {
                        updateListShift(request, date, shiftDtoList);
                    }
                    manageEmployeesShiftPresenter.presentRepeatShift(new Result<>(new ShiftRepeatResponse(shiftDtoList)));
                    break;
                case "EVERY_WEEK":
                    for (LocalDate date = request.getStart(); date.isBefore(request.getEnd().plusDays(1)); date = date.plusWeeks(1)) {
                        updateListShift(request, date, shiftDtoList);
                    }
                    manageEmployeesShiftPresenter.presentRepeatShift(new Result<>(new ShiftRepeatResponse(shiftDtoList)));
                    break;
                case "EVERY_MONTH":
                    for (LocalDate date = request.getStart(); date.isBefore(request.getEnd().plusDays(1)); date = date.plusMonths(1)) {
                        updateListShift(request, date, shiftDtoList);
                    }
                    manageEmployeesShiftPresenter.presentRepeatShift(new Result<>(new ShiftRepeatResponse(shiftDtoList)));
                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + request.getOption());
            }
        } catch (Request.NullRequestException e) {
            manageEmployeesShiftPresenter.presentRepeatedShiftNullRequest();
        } catch (Request.InvalidRequestException e) {
           manageEmployeesShiftPresenter.presentInvalidRepeatedShiftListRequest(request);
        }
    }

    @Override
    public void createShift(CreateShiftRequest request) {
        try {
            Request.validate(request);
            Employee employee = EmployeeDataMapper.matToEntity(request.getEmployee());
            String date = request.getDate().toString();
            String start = request.getStart().toString();
            String end = request.getEnd().toString();
            Shift shift = employee.createShift(date, start, end, HallDataMapper.mapToEntity(request.getHall()));
            manageEmployeesShiftPresenter.presentCreateShift(new Result<>(new CreateShiftResponse(ShiftDataMapper.mapToDto(shift))));
        } catch (Request.NullRequestException e) {
            manageEmployeesShiftPresenter.presentCreateShiftNullRequest();
        } catch (Request.InvalidRequestException e) {
           manageEmployeesShiftPresenter.presentInvalidCreateShiftListRequest(request);
        }
    }

    private void updateListShift(ShiftRepeatRequest request, LocalDate date, List<ShiftDto> shiftDtoList) {
        ShiftDto shiftDto;
        if (request.getShift().getEmployee() instanceof UsherDto) {
            shiftDto = new ShiftDto(request.getShift().getEmployee(),
                    date,
                    request.getShift().getStart(),
                    request.getShift().getEnd());
        } else {
            shiftDto = new ShiftProjectionistDto(request.getShift().getEmployee(),
                    date,
                    request.getShift().getStart(),
                    request.getShift().getEnd(),
                    ((ShiftProjectionistDto) request.getShift()).getHallDto());
        }
        try {
            shiftRepository.saveShift(ShiftDataMapper.mapToEntity(shiftDto));
            shiftDtoList.add(shiftDto);
        } catch (ShiftSaveException e) {
            manageEmployeesShiftPresenter.presentSaveShiftError(e);
        }

    }

}
