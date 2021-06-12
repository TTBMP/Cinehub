package com.ttbmp.cinehub.app.usecase.manageemployeesshift;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.dto.employee.EmployeeDtoFactory;
import com.ttbmp.cinehub.app.dto.shift.ShiftDto;
import com.ttbmp.cinehub.app.dto.shift.ShiftDtoFactory;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.cinema.CinemaRepository;
import com.ttbmp.cinehub.app.repository.employee.EmployeeRepository;
import com.ttbmp.cinehub.app.repository.hall.HallRepository;
import com.ttbmp.cinehub.app.repository.shift.ShiftRepository;
import com.ttbmp.cinehub.app.repository.shift.projectionist.ProjectionistShiftRepository;
import com.ttbmp.cinehub.app.service.email.EmailService;
import com.ttbmp.cinehub.app.service.email.EmailServiceRequest;
import com.ttbmp.cinehub.app.service.security.SecurityService;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.reply.*;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.request.*;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;
import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.employee.Projectionist;
import com.ttbmp.cinehub.domain.security.Permission;
import com.ttbmp.cinehub.domain.shift.ModifyShiftException;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;
import com.ttbmp.cinehub.domain.shift.Shift;
import com.ttbmp.cinehub.domain.shift.factory.CreateShiftException;
import com.ttbmp.cinehub.domain.shift.factory.ShiftFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * @author Massimo Mazzetti
 */
public class ManageEmployeesShiftController implements ManageEmployeesShiftUseCase {

    private final ManageEmployeesShiftPresenter manageEmployeesShiftPresenter;

    private final ShiftRepository shiftRepository;
    private final CinemaRepository cinemaRepository;
    private final EmailService emailService;
    private final EmployeeRepository employeeRepository;
    private final HallRepository hallRepository;
    private final ProjectionistShiftRepository projectionistShiftRepository;
    private final SecurityService securityService;

    public ManageEmployeesShiftController(ServiceLocator serviceLocator, ManageEmployeesShiftPresenter manageEmployeesShiftPresenter) {
        this.manageEmployeesShiftPresenter = manageEmployeesShiftPresenter;
        this.shiftRepository = serviceLocator.getService(ShiftRepository.class);
        this.cinemaRepository = serviceLocator.getService(CinemaRepository.class);
        this.emailService = serviceLocator.getService(EmailService.class);
        this.employeeRepository = serviceLocator.getService(EmployeeRepository.class);
        this.hallRepository = serviceLocator.getService(HallRepository.class);
        this.projectionistShiftRepository = serviceLocator.getService(ProjectionistShiftRepository.class);
        this.securityService = serviceLocator.getService(SecurityService.class);
    }

    @Override
    public void getCinemaList(GetCinemaListRequest request) {
        var permissions = new Permission[]{Permission.GET_SHIFT_LIST};
        try {
            AuthenticatedRequest.validate(request, securityService, permissions);
            var cinemaList = cinemaRepository.getAllCinema().stream()
                    .map(CinemaDto::new)
                    .collect(Collectors.toList());
            manageEmployeesShiftPresenter.presentCinemaList(new GetCinemaListReply(cinemaList));
        } catch (RepositoryException e) {
            manageEmployeesShiftPresenter.presentRepositoryError(e);
        } catch (AuthenticatedRequest.UnauthorizedRequestException e) {
            manageEmployeesShiftPresenter.presentUnauthorizedError(e);
        } catch (AuthenticatedRequest.UnauthenticatedRequestException e) {
            manageEmployeesShiftPresenter.presentUnauthenticatedError(e);
        } catch (Request.NullRequestException e) {
            manageEmployeesShiftPresenter.presentNullRequest();
        } catch (Request.InvalidRequestException e) {
            manageEmployeesShiftPresenter.presentInvalidRequest(request);
        }
    }

    @Override
    public void getEmployeeList(GetEmployeeListRequest request) {
        var permissions = new Permission[]{Permission.GET_EMPLOYEE_LIST};
        try {
            AuthenticatedRequest.validate(request, securityService, permissions);
            var cinema = cinemaRepository.getCinema(request.getCinemaId());
            if (cinema == null) {
                request.addError(GetEmployeeListRequest.INVALID_CINEMA);
                throw new Request.InvalidRequestException();
            }
            var employeeList = employeeRepository.getEmployeeList(cinema).stream()
                    .map(EmployeeDtoFactory::getEmployeeDto)
                    .collect(Collectors.toList());
            manageEmployeesShiftPresenter.presentEmployeeList(new GetEmployeeListReply(employeeList));
        } catch (RepositoryException e) {
            manageEmployeesShiftPresenter.presentRepositoryError(e);
        } catch (Request.NullRequestException e) {
            manageEmployeesShiftPresenter.presentNullRequest();
        } catch (Request.InvalidRequestException e) {
            manageEmployeesShiftPresenter.presentInvalidRequest(request);
        } catch (AuthenticatedRequest.UnauthorizedRequestException e) {
            manageEmployeesShiftPresenter.presentUnauthorizedError(e);
        } catch (AuthenticatedRequest.UnauthenticatedRequestException e) {
            manageEmployeesShiftPresenter.presentUnauthenticatedError(e);
        }
    }

    @Override
    public void getShiftList(GetShiftListRequest request) {
        var permissions = new Permission[]{Permission.GET_SHIFT_LIST};
        try {
            AuthenticatedRequest.validate(request, securityService, permissions);
            var cinema = cinemaRepository.getCinema(request.getCinemaId());
            if (cinema == null) {
                request.addError(GetShiftListRequest.INVALID_CINEMA);
                throw new Request.InvalidRequestException();
            }
            var shiftList = shiftRepository.getCinemaShiftListBetween(cinema, request.getStart(), request.getEnd()).stream()
                    .map(ShiftDtoFactory::getShiftDto)
                    .collect(Collectors.toList());
            manageEmployeesShiftPresenter.presentShiftList(new GetShiftListReply(
                    shiftList,
                    request.getStart(),
                    request.getCinemaId()
            ));
        } catch (Request.NullRequestException e) {
            manageEmployeesShiftPresenter.presentNullRequest();
        } catch (Request.InvalidRequestException e) {
            manageEmployeesShiftPresenter.presentInvalidRequest(request);
        } catch (RepositoryException e) {
            manageEmployeesShiftPresenter.presentRepositoryError(e);
        } catch (AuthenticatedRequest.UnauthorizedRequestException e) {
            manageEmployeesShiftPresenter.presentUnauthorizedError(e);
        } catch (AuthenticatedRequest.UnauthenticatedRequestException e) {
            manageEmployeesShiftPresenter.presentUnauthenticatedError(e);
        }
    }

    @Override
    public void modifyShift(ShiftModifyRequest request) {
        var permissions = new Permission[]{Permission.MODIFY_SHIFT};
        try {
            AuthenticatedRequest.validate(request, securityService, permissions);
            var shift = shiftRepository.getShift(request.getShiftId());
            var employee = employeeRepository.getEmployee(request.getEmployeeId());
            Hall hall = null;
            if (employee instanceof Projectionist) {
                hall = hallRepository.getHall(request.getHallId());
            }
            semanticValidationModifyShift(request, shift, employee, hall);

            shift.modifyShift(shift, request.getDate(), request.getStart(), request.getEnd(), hall);


            if (employee instanceof Projectionist) {
                projectionistShiftRepository.modifyShift((ProjectionistShift) shift);
            }
            shiftRepository.modifyShift(shift);
            emailService.sendMail(new EmailServiceRequest(
                    employee.getEmail(),
                    "Shift Modify"
            ));
            manageEmployeesShiftPresenter.presentCreateShift(new CreateShiftReply(ShiftDtoFactory.getShiftDto(shift)));
            manageEmployeesShiftPresenter.presentDeleteShift();
            manageEmployeesShiftPresenter.presentSaveShift();

        } catch (Request.NullRequestException e) {
            manageEmployeesShiftPresenter.presentNullRequest();
        } catch (Request.InvalidRequestException e) {
            manageEmployeesShiftPresenter.presentInvalidRequest(request);
        } catch (ModifyShiftException e) {
            manageEmployeesShiftPresenter.presentModifyShiftError(e);
        } catch (RepositoryException e) {
            manageEmployeesShiftPresenter.presentRepositoryError(e);
        } catch (AuthenticatedRequest.UnauthorizedRequestException e) {
            manageEmployeesShiftPresenter.presentUnauthorizedError(e);
        } catch (AuthenticatedRequest.UnauthenticatedRequestException e) {
            manageEmployeesShiftPresenter.presentUnauthenticatedError(e);
        }
    }

    private void semanticValidationModifyShift(ShiftModifyRequest request, Shift shift, Employee employee, Hall hall) throws Request.InvalidRequestException {
        if (shift == null) {
            request.addError(ShiftModifyRequest.MISSING_SHIFT);
        } else if (LocalDate.now().plusDays(1).isAfter(LocalDate.parse(shift.getDate()))) {
            request.addError(ShiftModifyRequest.INVALID_SHIFT);
        }
        if (employee == null) {
            request.addError(ShiftModifyRequest.MISSING_EMPLOYEE);
        }
        if (hall == null && employee instanceof Projectionist) {
            request.addError(ShiftModifyRequest.MISSING_HALL);
        }
        if (!request.getErrorList().isEmpty()) {
            throw new Request.InvalidRequestException();
        }
    }

    @Override
    public void deleteShift(ShiftRequest request) {
        var permissions = new Permission[]{Permission.DELETE_SHIFT};
        try {
            AuthenticatedRequest.validate(request, securityService, permissions);
            var shift = shiftRepository.getShift(request.getShiftId());
            semanticValidationDeleteShift(request, shift);
            var email = shift.getEmployee().getEmail();
            shiftRepository.deletedShift(shift);
            manageEmployeesShiftPresenter.presentDeleteShift();
            emailService.sendMail(new EmailServiceRequest(
                    email,
                    "Shift Delete"
            ));
        } catch (Request.NullRequestException e) {
            manageEmployeesShiftPresenter.presentNullRequest();
        } catch (Request.InvalidRequestException e) {
            manageEmployeesShiftPresenter.presentInvalidRequest(request);
        } catch (RepositoryException e) {
            manageEmployeesShiftPresenter.presentRepositoryError(e);
        } catch (AuthenticatedRequest.UnauthorizedRequestException e) {
            manageEmployeesShiftPresenter.presentUnauthorizedError(e);
        } catch (AuthenticatedRequest.UnauthenticatedRequestException e) {
            manageEmployeesShiftPresenter.presentUnauthenticatedError(e);
        }
    }

    private void semanticValidationDeleteShift(ShiftRequest request, Shift shift) throws Request.InvalidRequestException {
        if (shift == null) {
            request.addError(ShiftRequest.MISSING_SHIFT);
        } else if (LocalDate.now().plusDays(1).isAfter(LocalDate.parse(shift.getDate()))) {
            request.addError(ShiftRequest.INVALID_SHIFT);
        }
        if (!request.getErrorList().isEmpty()) {
            throw new Request.InvalidRequestException();
        }

    }

    @Override
    public void createRepeatedShift(ShiftRepeatRequest request) {
        var permissions = new Permission[]{Permission.ASSIGN_SHIFT};
        try {
            AuthenticatedRequest.validate(request, securityService, permissions);
            List<Shift> shiftList = new ArrayList<>();
            UnaryOperator<LocalDate> increaseDateFunction;
            var employee = employeeRepository.getEmployee(request.getEmployeeId());
            Hall hall = null;
            if (employee instanceof Projectionist) {
                hall = hallRepository.getHall(request.getHallId());
            }
            semanticValidationCreateShift(request, employee, hall);
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
                        hall
                );

                shiftList.add(shift);
            }
            List<ShiftDto> shiftDtoList = new ArrayList<>();
            for (var shift : shiftList) {
                saveShift(shift);
                shiftDtoList.add(ShiftDtoFactory.getShiftDto(shift));
            }

            emailService.sendMail(new EmailServiceRequest(
                    employee.getEmail(),
                    "Shift Modify"
            ));
            manageEmployeesShiftPresenter.presentRepeatShift(new ShiftRepeatReply(shiftDtoList));
        } catch (Request.NullRequestException e) {
            manageEmployeesShiftPresenter.presentNullRequest();
        } catch (Request.InvalidRequestException e) {
            manageEmployeesShiftPresenter.presentInvalidRequest(request);
        } catch (CreateShiftException e) {
            manageEmployeesShiftPresenter.presentCreateShiftError(e);
        } catch (RepositoryException e) {
            manageEmployeesShiftPresenter.presentRepositoryError(e);
        } catch (AuthenticatedRequest.UnauthorizedRequestException e) {
            manageEmployeesShiftPresenter.presentUnauthorizedError(e);
        } catch (AuthenticatedRequest.UnauthenticatedRequestException e) {
            manageEmployeesShiftPresenter.presentUnauthenticatedError(e);
        }
    }


    @Override
    public void createShift(CreateShiftRequest request) {
        var permissions = new Permission[]{Permission.ASSIGN_SHIFT};
        try {
            AuthenticatedRequest.validate(request, securityService, permissions);
            var employee = employeeRepository.getEmployee(request.getEmployeeId());
            var date = request.getDate().toString();
            var start = request.getStart().toString();
            var end = request.getEnd().toString();
            var hall = hallRepository.getHall(request.getHallId());
            semanticValidationCreateShift(request, employee, hall);
            var shiftFactory = new ShiftFactory();
            var shift = shiftFactory.createConcreteShift(employee, date, start, end, hall);
            saveShift(shift);
            shift = shiftRepository.getShift(employee, date, start, end);
            manageEmployeesShiftPresenter.presentCreateShift(new CreateShiftReply(ShiftDtoFactory.getShiftDto(shift)));
            manageEmployeesShiftPresenter.presentSaveShift();
            emailService.sendMail(new EmailServiceRequest(
                    employee.getEmail(),
                    "Shift Assign"
            ));
        } catch (Request.NullRequestException e) {
            manageEmployeesShiftPresenter.presentNullRequest();
        } catch (Request.InvalidRequestException e) {
            manageEmployeesShiftPresenter.presentInvalidRequest(request);
        } catch (CreateShiftException e) {
            manageEmployeesShiftPresenter.presentCreateShiftError(e);
        } catch (RepositoryException e) {
            manageEmployeesShiftPresenter.presentRepositoryError(e);
        } catch (AuthenticatedRequest.UnauthorizedRequestException e) {
            manageEmployeesShiftPresenter.presentUnauthorizedError(e);
        } catch (AuthenticatedRequest.UnauthenticatedRequestException e) {
            manageEmployeesShiftPresenter.presentUnauthenticatedError(e);
        }
    }

    private void semanticValidationCreateShift(Request request, Employee employee, Hall hall) throws Request.InvalidRequestException {
        if (employee == null) {
            request.addError(CreateShiftRequest.MISSING_EMPLOYEE);
        }
        if (hall == null && employee instanceof Projectionist) {
            request.addError(CreateShiftRequest.MISSING_HALL);
        }
        if (!request.getErrorList().isEmpty()) {
            throw new Request.InvalidRequestException();
        }

    }

    private void saveShift(Shift shift) throws RepositoryException {
        shiftRepository.saveShift(shift);
        var tmpShift = shiftRepository.getShift(shift.getEmployee(), shift.getDate(), shift.getStart(), shift.getEnd());
        shift.setId(tmpShift.getId());
        if (shift.getEmployee() instanceof Projectionist) {
            projectionistShiftRepository.saveShift((ProjectionistShift) shift);
        }
    }

}
