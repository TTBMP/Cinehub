package com.ttbmp.cinehub.app.usecase.manageemployeesshift;

import com.ttbmp.cinehub.app.datamapper.CinemaDataMapper;
import com.ttbmp.cinehub.app.datamapper.EmployeeDataMapper;
import com.ttbmp.cinehub.app.datamapper.HallDataMapper;
import com.ttbmp.cinehub.app.datamapper.ShiftDataMapper;
import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.dto.ShiftDto;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.cinema.CinemaRepository;
import com.ttbmp.cinehub.app.repository.employee.EmployeeRepository;
import com.ttbmp.cinehub.app.repository.shift.ShiftRepository;
import com.ttbmp.cinehub.app.service.email.EmailService;
import com.ttbmp.cinehub.app.service.email.EmailServiceRequest;
import com.ttbmp.cinehub.app.usecase.Request;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.*;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.response.CreateShiftResponse;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.response.GetCinemaListResponse;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.response.GetShiftListResponse;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.response.ShiftRepeatResponse;
import com.ttbmp.cinehub.domain.shift.ModifyShiftException;
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
    private final EmailService emailService;
    private final EmployeeRepository employeeRepository;

    public ManageEmployeesShiftController(ServiceLocator serviceLocator, ManageEmployeesShiftPresenter manageEmployeesShiftPresenter) {
        this.manageEmployeesShiftPresenter = manageEmployeesShiftPresenter;
        this.shiftRepository = serviceLocator.getService(ShiftRepository.class);
        this.cinemaRepository = serviceLocator.getService(CinemaRepository.class);
        this.emailService = serviceLocator.getService(EmailService.class);
        this.employeeRepository = serviceLocator.getService(EmployeeRepository.class);
    }

    @Override
    public void getCinemaList() {
        try {
            manageEmployeesShiftPresenter.presentCinemaList(new GetCinemaListResponse(
                    CinemaDataMapper.mapToDtoList(cinemaRepository.getAllCinema())
            ));
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getShiftList(GetShiftListRequest request) {
        try {
            Request.validate(request);
            manageEmployeesShiftPresenter.presentShiftList(new GetShiftListResponse(
                    ShiftDataMapper.mapToDtoList(shiftRepository.getAllShift()),
                    request.getStart(),
                    request.getCinema().getId()
            ));
        } catch (Request.NullRequestException e) {
            manageEmployeesShiftPresenter.presentGetShiftListNullRequest();
        } catch (Request.InvalidRequestException e) {
            manageEmployeesShiftPresenter.presentInvalidGetShiftListRequest(request);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifyShift(ShiftModifyRequest request) {
        try {
            Request.validate(request);
            var shift = shiftRepository.getShift(request.getShiftId());
            var employee = employeeRepository.getEmployee(request.getEmployeeDto().getId());
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
        } catch (ModifyShiftException e) {
            manageEmployeesShiftPresenter.presentModifyShiftError(e);
        } catch (RepositoryException e) {
            e.printStackTrace();
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
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveRepeatedShift(ShiftRepeatRequest request) {
        try {
            Request.validate(request);
            List<ShiftDto> shiftDtoList = new ArrayList<>();
            UnaryOperator<LocalDate> increaseDateFunction;
            var employee = employeeRepository.getEmployee(request.getEmployeeDto().getId());
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
            for (var date = request.getStart(); date.isBefore(request.getEnd().plusDays(1)); date = increaseDateFunction.apply(date)) {
                var shiftFactory = new ShiftFactory();
                var shift = shiftFactory.createConcreteShift(
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
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createShift(CreateShiftRequest request) {
        try {
            Request.validate(request);
            var employee = employeeRepository.getEmployee(request.getEmployee().getId());
            var date = request.getDate().toString();
            var start = request.getStart().toString();
            var end = request.getEnd().toString();
            var shiftFactory = new ShiftFactory();
            var shift = shiftFactory.createConcreteShift(employee, date, start, end, HallDataMapper.mapToEntity(request.getHall()));

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
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }


}