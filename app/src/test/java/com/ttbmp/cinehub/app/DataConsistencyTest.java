package com.ttbmp.cinehub.app;

import com.ttbmp.cinehub.app.di.JdbcServiceLocator;
import com.ttbmp.cinehub.app.di.MockServiceLocator;
import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.cinema.CinemaRepository;
import com.ttbmp.cinehub.app.repository.employee.EmployeeRepository;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.Seat;
import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.shift.Shift;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Disabled
class DataConsistencyTest {

    private final MockServiceLocator mockServiceLocator = new MockServiceLocator();
    private final JdbcServiceLocator jdbcServiceLocator = new JdbcServiceLocator();

    @Test
    void cinema() throws RepositoryException {
        var mockCinema = mockServiceLocator.getService(CinemaRepository.class).getAllCinema();
        var jdbcCinema = jdbcServiceLocator.getService(CinemaRepository.class).getAllCinema();
        Assertions.assertEquals(mockCinema, jdbcCinema);
    }

    @Test
    void employee() throws RepositoryException {
        var mockEmployee = getAllEmployee(mockServiceLocator);
        var jdbcEmployee = getAllEmployee(jdbcServiceLocator);

        Assertions.assertEquals(mockEmployee, jdbcEmployee);
    }

    private Set<Employee> getAllEmployee(ServiceLocator serviceLocator) throws RepositoryException {
        var mockCinema = mockServiceLocator.getService(CinemaRepository.class).getAllCinema();
        List<Employee> employeeList = new ArrayList<>();
        for (var cinema : mockCinema) {
            employeeList.addAll(serviceLocator.getService(EmployeeRepository.class).getEmployeeList(cinema));
        }
        return new HashSet<>(employeeList);
    }

    @Test
    void hall() throws RepositoryException {
        var mockHall = getAllHall(mockServiceLocator);
        var jdbcHall = getAllHall(jdbcServiceLocator);
        Assertions.assertEquals(mockHall, jdbcHall);
    }

    private Set<Hall> getAllHall(ServiceLocator serviceLocator) throws RepositoryException {
        var cinemaList = serviceLocator.getService(CinemaRepository.class).getAllCinema();
        return cinemaList.stream()
                .map(Cinema::getHallList)
                .flatMap(List::stream)
                .collect(Collectors.toSet());
    }


    @Test
    void seat() throws RepositoryException {
        Set<Seat> mockSeat = getAllSeat(mockServiceLocator);
        Set<Seat> jdbcSeat = getAllSeat(jdbcServiceLocator);
        Assertions.assertEquals(mockSeat, jdbcSeat);
    }

    private Set<Seat> getAllSeat(ServiceLocator serviceLocator) throws RepositoryException {
        var cinemaList = serviceLocator.getService(CinemaRepository.class).getAllCinema();
        var hallList = cinemaList.stream()
                .map(Cinema::getHallList)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        return hallList.stream()
                .map(Hall::getSeatList)
                .flatMap(List::stream)
                .collect(Collectors.toSet());
    }

    @Test
    void shift() throws RepositoryException {
        Set<Shift> mockShift = getAllShift(mockServiceLocator);
        Set<Shift> jdbcShift = getAllShift(jdbcServiceLocator);
        Assertions.assertEquals(mockShift, jdbcShift);
    }

    private Set<Shift> getAllShift(ServiceLocator serviceLocator) throws RepositoryException {
        var cinemaList = serviceLocator.getService(CinemaRepository.class).getAllCinema();
        List<Employee> employeeList = new ArrayList<>();
        for (var cinema : cinemaList) {
            employeeList.addAll(mockServiceLocator.getService(EmployeeRepository.class).getEmployeeList(cinema));
        }
        return employeeList.stream().map(Employee::getShiftList).flatMap(List::stream).collect(Collectors.toSet());
    }


}
