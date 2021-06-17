package com.ttbmp.cinehub.app.repository;

import com.ttbmp.cinehub.app.di.JdbcServiceLocator;
import com.ttbmp.cinehub.app.di.MockServiceLocator;
import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.cinema.CinemaRepository;
import com.ttbmp.cinehub.app.repository.employee.EmployeeRepository;
import com.ttbmp.cinehub.app.repository.movie.MovieRepository;
import com.ttbmp.cinehub.app.repository.projection.ProjectionRepository;
import com.ttbmp.cinehub.app.repository.user.UserRepository;
import com.ttbmp.cinehub.domain.*;
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
class MockRepositoryTest {

    private final MockServiceLocator mockServiceLocator = new MockServiceLocator();
    private final JdbcServiceLocator jdbcServiceLocator = new JdbcServiceLocator();

    @Test
    void employee() throws RepositoryException {
        var mockEmployeeSet = getAllEmployee(mockServiceLocator);
        var jdbcEmployeeSet = getAllEmployee(jdbcServiceLocator);
        Assertions.assertEquals(jdbcEmployeeSet, mockEmployeeSet);
    }

    @Test
    void shift() throws RepositoryException {
        var mockShiftSet = getAllShift(mockServiceLocator);
        var jdbcShiftSet = getAllShift(jdbcServiceLocator);
        Assertions.assertEquals(jdbcShiftSet, mockShiftSet);
    }

    @Test
    void cinema() throws RepositoryException {
        var mockCinemaSet = getAllCinema(mockServiceLocator);
        var jdbcCinemaSet = getAllCinema(jdbcServiceLocator);
        Assertions.assertEquals(jdbcCinemaSet, mockCinemaSet);
    }

    @Test
    void hall() throws RepositoryException {
        var mockHallSet = getAllHall(mockServiceLocator);
        var jdbcHallSet = getAllHall(jdbcServiceLocator);
        Assertions.assertEquals(jdbcHallSet, mockHallSet);
    }

    @Test
    void movie() throws RepositoryException {
        var mockMovieSet = getAllMovie(mockServiceLocator);
        var jdbcMovieSet = getAllMovie(jdbcServiceLocator);
        Assertions.assertEquals(jdbcMovieSet, mockMovieSet);
    }

    @Test
    void projection() throws RepositoryException {
        var mockSeatSet = getAllProjection(mockServiceLocator);
        var jdbcSeatSet = getAllProjection(jdbcServiceLocator);
        Assertions.assertEquals(jdbcSeatSet, mockSeatSet);
    }

    @Test
    void seat() throws RepositoryException {
        var mockSeatSet = getAllSeat(mockServiceLocator);
        var jdbcSeatSet = getAllSeat(jdbcServiceLocator);
        Assertions.assertEquals(jdbcSeatSet, mockSeatSet);
    }

    @Test
    void user() throws RepositoryException {
        var mockUserSet = getAllUser(mockServiceLocator);
        var jdbcUserSet = getAllUser(jdbcServiceLocator);
        Assertions.assertEquals(jdbcUserSet, mockUserSet);
    }

    private Set<Cinema> getAllCinema(ServiceLocator serviceLocator) throws RepositoryException {
        return new HashSet<>(serviceLocator.getService(CinemaRepository.class).getAllCinema());
    }

    private Set<Employee> getAllEmployee(ServiceLocator serviceLocator) throws RepositoryException {
        return new HashSet<>(serviceLocator.getService(EmployeeRepository.class).getAllEmployee());
    }

    private Set<Shift> getAllShift(ServiceLocator serviceLocator) throws RepositoryException {
        var cinemaList = serviceLocator.getService(CinemaRepository.class).getAllCinema();
        List<Employee> employeeList = new ArrayList<>();
        for (var cinema : cinemaList) {
            employeeList.addAll(mockServiceLocator.getService(EmployeeRepository.class).getEmployeeList(cinema));
        }
        return employeeList.stream().map(Employee::getShiftList).flatMap(List::stream).collect(Collectors.toSet());
    }

    private Set<Hall> getAllHall(ServiceLocator serviceLocator) throws RepositoryException {
        var cinemaList = serviceLocator.getService(CinemaRepository.class).getAllCinema();
        return cinemaList.stream()
                .map(Cinema::getHallList)
                .flatMap(List::stream)
                .collect(Collectors.toSet());
    }

    private Set<Movie> getAllMovie(ServiceLocator serviceLocator) throws RepositoryException {
        return new HashSet<>(serviceLocator.getService(MovieRepository.class).getAllMovie());
    }

    private Set<Projection> getAllProjection(ServiceLocator serviceLocator) throws RepositoryException {
        return new HashSet<>(serviceLocator.getService(ProjectionRepository.class).getAllProjection());
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

    private Set<User> getAllUser(ServiceLocator serviceLocator) throws RepositoryException {
        return new HashSet<>(serviceLocator.getService(UserRepository.class).getAllUser());
    }

}
