package com.ttbmp.cinehub.app.usecase.manageemployeesshift;

import com.ttbmp.cinehub.app.ShiftSaveException;
import com.ttbmp.cinehub.app.datamapper.CinemaDataMapper;
import com.ttbmp.cinehub.app.datamapper.EmployeeDataMapper;
import com.ttbmp.cinehub.app.datamapper.HallDataMapper;
import com.ttbmp.cinehub.app.datamapper.ShiftDataMapper;
import com.ttbmp.cinehub.app.dto.ShiftDto;
import com.ttbmp.cinehub.domain.Employee;
import com.ttbmp.cinehub.domain.shift.Shift;
import com.ttbmp.cinehub.domain.shift.ShiftFactory;
import com.ttbmp.cinehub.app.repository.CinemaRepository;
import com.ttbmp.cinehub.app.repository.HallRepository;
import com.ttbmp.cinehub.app.repository.ShiftRepository;
import com.ttbmp.cinehub.app.service.email.EmailService;
import com.ttbmp.cinehub.app.service.email.EmailServiceRequest;
import com.ttbmp.cinehub.app.usecase.Request;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.*;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.response.*;
import com.ttbmp.cinehub.app.utilities.result.Result;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;


/**
 * @author Massimo Mazzetti
 */
public class ManageEmployeesShiftController implements ManageEmployeesShiftUseCase {
    private final ManageEmployeesShiftPresenter manageEmployeesShiftPresenter;
    private final ShiftRepository shiftRepository;
    private final CinemaRepository cinemaRepository;
    private final HallRepository hallRepository;
    private final EmailService emailService;

    public ManageEmployeesShiftController(ManageEmployeesShiftPresenter manageEmployeesShiftPresenter,
                                          ShiftRepository shiftRepository,
                                          CinemaRepository cinemaRepository,
                                          HallRepository hallRepository,
                                          EmailService emailService) {
        this.manageEmployeesShiftPresenter = manageEmployeesShiftPresenter;
        this.shiftRepository = shiftRepository;
        this.cinemaRepository = cinemaRepository;
        this.hallRepository = hallRepository;
        this.emailService = emailService;
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
            emailService.sendMail(new EmailServiceRequest(
                    EmployeeDataMapper.matToEntity(request.getShift().getEmployee()).getEmail(),
                    "Shift Assign"
            ));
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
            shiftRepository.modifyShift(ShiftDataMapper.mapToEntity(request.getOldShift()), ShiftDataMapper.mapToEntity(request.getNewShift()));
            manageEmployeesShiftPresenter.presentSaveShift();
            manageEmployeesShiftPresenter.presentDeleteShift();
            emailService.sendMail(new EmailServiceRequest(
                    EmployeeDataMapper.matToEntity(request.getNewShift().getEmployee()).getEmail(),
                    "Shift Modify"
            ));
        } catch (Request.NullRequestException e) {
            manageEmployeesShiftPresenter.presentModifyShiftNullRequest();
        } catch (Request.InvalidRequestException e) {
            manageEmployeesShiftPresenter.presentInvalidModifyShiftListRequest(request);
        } catch (ShiftSaveException e) {
            manageEmployeesShiftPresenter.presentModifyShiftError(e);
        }

    }

    @Override
    public void deleteShift(ShiftRequest request) {
        try {
            Request.validate(request);
            shiftRepository.deletedShift(ShiftDataMapper.mapToEntity(request.getShift()));
            manageEmployeesShiftPresenter.presentDeleteShift();
            emailService.sendMail(new EmailServiceRequest(
                    EmployeeDataMapper.matToEntity(request.getShift().getEmployee()).getEmail(),
                    "Shift Delete"
            ));
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
            UnaryOperator<LocalDate> increaseDateFunction;
            switch (request.getOption()) {
                case "EVERY_DAY":
                    increaseDateFunction = date -> date.plusDays(1);
                    break;
                case "EVERY_WEEK":
                    increaseDateFunction = date -> date.plusWeeks(1);
                    break;
                case "EVERY_MONTH":
                    increaseDateFunction = date -> date.plusMonths(1);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + request.getOption());
            }
            for (LocalDate date = request.getStart(); date.isBefore(request.getEnd().plusDays(1)); date = increaseDateFunction.apply(date)) {
                ShiftFactory shiftFactory = new ShiftFactory();
                Shift shift = shiftFactory.createConcreteShift(
                        EmployeeDataMapper.matToEntity(request.getShift().getEmployee()),
                        date.toString(),
                        request.getShift().getStart().toString(),
                        request.getShift().getEnd().toString(),
                        HallDataMapper.mapToEntity(request.getHall())
                );
                shiftRepository.saveShift(shift);
                shiftDtoList.add(ShiftDataMapper.mapToDto(shift));
            }
            emailService.sendMail(new EmailServiceRequest(
                    EmployeeDataMapper.matToEntity(request.getShift().getEmployee()).getEmail(),
                    "Shift Modify"
            ));
            manageEmployeesShiftPresenter.presentRepeatShift(new Result<>(new ShiftRepeatResponse(shiftDtoList)));
        } catch (Request.NullRequestException e) {
            manageEmployeesShiftPresenter.presentRepeatedShiftNullRequest();
        } catch (Request.InvalidRequestException e) {
            manageEmployeesShiftPresenter.presentInvalidRepeatedShiftListRequest(request);
        } catch (ShiftSaveException e) {
            manageEmployeesShiftPresenter.presentSaveShiftError(e);
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
            ShiftFactory shiftFactory = new ShiftFactory();
            Shift shift = shiftFactory.createConcreteShift(employee, date, start, end, HallDataMapper.mapToEntity(request.getHall()));

            manageEmployeesShiftPresenter.presentCreateShift(new Result<>(new CreateShiftResponse(ShiftDataMapper.mapToDto(shift))));
        } catch (Request.NullRequestException e) {
            manageEmployeesShiftPresenter.presentCreateShiftNullRequest();
        } catch (Request.InvalidRequestException e) {
            manageEmployeesShiftPresenter.presentInvalidCreateShiftListRequest(request);
        }
    }


}