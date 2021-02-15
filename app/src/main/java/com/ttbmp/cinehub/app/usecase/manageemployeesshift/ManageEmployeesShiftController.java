package com.ttbmp.cinehub.app.usecase.manageemployeesshift;

import com.ttbmp.cinehub.app.datamapper.CinemaDataMapper;
import com.ttbmp.cinehub.app.datamapper.EmployeeDataMapper;
import com.ttbmp.cinehub.app.datamapper.HallDataMapper;
import com.ttbmp.cinehub.app.datamapper.ShiftDataMapper;
import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.dto.ShiftDto;
import com.ttbmp.cinehub.app.repository.cinema.CinemaRepository;
import com.ttbmp.cinehub.app.repository.employee.EmployeeRepository;
import com.ttbmp.cinehub.app.repository.hall.HallRepository;
import com.ttbmp.cinehub.app.repository.shift.ShiftRepository;
import com.ttbmp.cinehub.app.repository.shift.ShiftSaveException;
import com.ttbmp.cinehub.app.service.email.EmailService;
import com.ttbmp.cinehub.app.service.email.EmailServiceRequest;
import com.ttbmp.cinehub.app.usecase.Request;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.*;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.response.*;
import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.shift.ModifyShiftException;
import com.ttbmp.cinehub.domain.shift.Shift;
import com.ttbmp.cinehub.domain.shift.factory.CreateShiftException;
import com.ttbmp.cinehub.domain.shift.factory.ShiftFactory;

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
    private final EmployeeRepository employeeRepository;

    public ManageEmployeesShiftController(ServiceLocator serviceLocator, ManageEmployeesShiftPresenter manageEmployeesShiftPresenter) {
        this.manageEmployeesShiftPresenter = manageEmployeesShiftPresenter;
        this.shiftRepository = serviceLocator.getService(ShiftRepository.class);
        this.cinemaRepository = serviceLocator.getService(CinemaRepository.class);
        this.hallRepository = serviceLocator.getService(HallRepository.class);
        this.emailService = serviceLocator.getService(EmailService.class);
        this.employeeRepository = serviceLocator.getService(EmployeeRepository.class);
    }

    @Override
    public void getCinemaList() {
        manageEmployeesShiftPresenter.presentCinemaList(new GetCinemaListResponse(
                CinemaDataMapper.mapToDtoList(cinemaRepository.getAllCinema())
        ));
    }

    @Override
    public void getHallList(GetHallListRequest request) {
        try {
            Request.validate(request);
            manageEmployeesShiftPresenter.presentHallList(new GetHallListResponse(
                    HallDataMapper.mapToDtoList(hallRepository.getHallList(
                            CinemaDataMapper.mapToEntity(request.getCinema())
                    ))
            ));
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
            manageEmployeesShiftPresenter.presentShiftList(new GetShiftListResponse(
                    ShiftDataMapper.mapToDtoList(shiftRepository.getShiftList()),
                    request.getStart(),
                    request.getCinema())
            );
        } catch (Request.NullRequestException e) {
            manageEmployeesShiftPresenter.presentGetShiftListNullRequest();
        } catch (Request.InvalidRequestException e) {
            manageEmployeesShiftPresenter.presentInvalidGetShiftListRequest(request);
        }
    }

    @Override
    public void modifyShift(ShiftModifyRequest request) {
        try {
            Request.validate(request);
            Shift shift = shiftRepository.getShift(request.getShiftId());
            Employee employee = employeeRepository.getEmployee(request.getEmployeeDto().getId());
            /* Hall hall= hallRepository.getHall(request.getHall().getId());*/
            shift.modifyShift(shift, request.getDate(), request.getStart(), request.getEnd(), HallDataMapper.mapToEntity(request.getHall()));
            shiftRepository.modifyShift(shift);
            emailService.sendMail(new EmailServiceRequest(
                    employee.getEmail(),
                    "Shift Modify"
            ));
            manageEmployeesShiftPresenter.presentCreateShift(new CreateShiftResponse(ShiftDataMapper.mapToDto(shift)));
            manageEmployeesShiftPresenter.presentDeleteShift();
            manageEmployeesShiftPresenter.presentSaveShift();

        } catch (Request.NullRequestException e) {
            manageEmployeesShiftPresenter.presentModifyShiftNullRequest();
        } catch (Request.InvalidRequestException e) {
            manageEmployeesShiftPresenter.presentInvalidModifyShiftListRequest(request);
        } catch (ShiftSaveException e) {
            manageEmployeesShiftPresenter.presentCreateShiftError(e);
        } catch (ModifyShiftException e) {
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
            Employee employee = employeeRepository.getEmployee(request.getEmployeeDto().getId());
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
                        employee,
                        date.toString(),
                        request.getStartShift().toString(),
                        request.getEndShift().toString(),
                        HallDataMapper.mapToEntity(request.getHall())
                );
                shiftRepository.saveShift(shift);
                shiftDtoList.add(ShiftDataMapper.mapToDto(shift));
            }
            emailService.sendMail(new EmailServiceRequest(
                    employee.getEmail(),
                    "Shift Modify"
            ));
            manageEmployeesShiftPresenter.presentRepeatShift(new ShiftRepeatResponse(shiftDtoList));
        } catch (Request.NullRequestException e) {
            manageEmployeesShiftPresenter.presentRepeatedShiftNullRequest();
        } catch (Request.InvalidRequestException e) {
            manageEmployeesShiftPresenter.presentInvalidRepeatedShiftListRequest(request);
        } catch (CreateShiftException e) {
            manageEmployeesShiftPresenter.presentCreateShiftError(e);
        }
    }

    @Override
    public void createShift(CreateShiftRequest request) {
        try {
            Request.validate(request);
            Employee employee = employeeRepository.getEmployee(request.getEmployee().getId());
            String date = request.getDate().toString();
            String start = request.getStart().toString();
            String end = request.getEnd().toString();
            ShiftFactory shiftFactory = new ShiftFactory();
            Shift shift = shiftFactory.createConcreteShift(employee, date, start, end, HallDataMapper.mapToEntity(request.getHall()));

            manageEmployeesShiftPresenter.presentCreateShift(new CreateShiftResponse(ShiftDataMapper.mapToDto(shift)));

            shiftRepository.saveShift(shift);
            manageEmployeesShiftPresenter.presentSaveShift();
            emailService.sendMail(new EmailServiceRequest(
                    employee.getEmail(),
                    "Shift Assign"
            ));

        } catch (Request.NullRequestException e) {
            manageEmployeesShiftPresenter.presentCreateShiftNullRequest();
        } catch (Request.InvalidRequestException e) {
            manageEmployeesShiftPresenter.presentInvalidCreateShiftListRequest(request);
        } catch (CreateShiftException e) {
            manageEmployeesShiftPresenter.presentCreateShiftError(e);
        }
    }


}
