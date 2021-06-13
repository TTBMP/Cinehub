package com.ttbmp.cinehub.app.usecase.buyticket;

import com.ttbmp.cinehub.app.di.MockServiceLocator;
import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.dto.MovieDto;
import com.ttbmp.cinehub.app.dto.ProjectionDto;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.cinema.CinemaRepository;
import com.ttbmp.cinehub.app.repository.movie.MovieRepository;
import com.ttbmp.cinehub.app.repository.projection.ProjectionRepository;
import com.ttbmp.cinehub.app.usecase.buyticket.request.CinemaListRequest;
import com.ttbmp.cinehub.app.usecase.buyticket.request.MovieListRequest;
import com.ttbmp.cinehub.app.usecase.buyticket.request.ProjectionListRequest;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


class BuyTicketBuyTicketControllerTest {

    private  MockServiceLocator serviceLocator;
    private  MockBuyTicketViewModel viewModel;
    private BuyTicketController controller;

    @BeforeEach
    void setPresenter() {
        serviceLocator = new MockServiceLocator();
        viewModel = new MockBuyTicketViewModel();
        controller = new BuyTicketController(
                serviceLocator,
                new MockBuyTicketPresenter(viewModel)
        );
    }

    private void logInAsCustomer() {
        viewModel.setSessionToken("5KClU7hbNgedJAwLuF9eFVl6Qzz2");
    }

    @Test
    void getListMovie_whitCorrectRequest_notGenerateErrors() throws RepositoryException {
        var request = new MovieListRequest(LocalDate.now());
        controller.getMovieList(request);
        var expected = getMovieListExpected();
        var actual = viewModel.getMovieList();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getListMovie_whitNullDate_generateErrors() throws RepositoryException {
        var request = new MovieListRequest(null);
        controller.getMovieList(request);
        Assertions.assertNotNull(viewModel.getErrorMessage());
    }

    private List<MovieDto> getMovieListExpected() throws RepositoryException {
        return serviceLocator.getService(MovieRepository.class).getMovieList(String.valueOf(LocalDate.now())).stream()
                .map(MovieDto::new)
                .collect(Collectors.toList());
    }

    @Test
    void getListCinema_whitCorrectRequest_notGenerateErrors() throws RepositoryException {
        var movieRepository = serviceLocator.getService(MovieRepository.class);
        var movie = movieRepository.getMovie(getMovieListExpected().get(0).getId());
        var requestCinema = new CinemaListRequest(movie.getId(),String.valueOf(LocalDate.now()));
        controller.getCinemaList(requestCinema);
        var expected = getCinemaListExpected(movie);
        var actual = viewModel.getCinemaList();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getListCinema_whitNullDate_generateErrors() throws RepositoryException {
        var movieRepository = serviceLocator.getService(MovieRepository.class);
        var movie = movieRepository.getMovie(getMovieListExpected().get(0).getId());
        var request = new CinemaListRequest(movie.getId(),null);
        controller.getCinemaList(request);
        Assertions.assertNotNull(viewModel.getErrorMessage());
    }

    private List<CinemaDto> getCinemaListExpected(Movie movie) throws RepositoryException {
        return serviceLocator.getService(CinemaRepository.class).getListCinema(movie,String.valueOf(LocalDate.now())).stream()
                .map(CinemaDto::new)
                .collect(Collectors.toList());
    }

    @Test
    void getListProjection_whitCorrectRequest_notGenerateErrors() throws RepositoryException {
        var movieRepository = serviceLocator.getService(MovieRepository.class);
        var movie = movieRepository.getMovie(getMovieListExpected().get(0).getId());
        var cinemaRepository = serviceLocator.getService(CinemaRepository.class);
        var cinema = cinemaRepository.getCinema(getCinemaListExpected(movie).get(0).getId());
        var request = new ProjectionListRequest(movie.getId(),cinema.getId(),LocalDate.now());
        controller.getProjectionList(request);
        var expected = getProjectionListExpected(movie,cinema);
        var actual = viewModel.getProjectionList();
        Assertions.assertEquals(expected, actual);
    }


    private List<ProjectionDto> getProjectionListExpected(Movie movie, Cinema cinema) throws RepositoryException {
        return serviceLocator.getService(ProjectionRepository.class).getProjectionList(cinema,movie,String.valueOf(LocalDate.now())).stream()
                .map(ProjectionDto::new)
                .collect(Collectors.toList());
    }

/*
    @Test
    void getListSeat_whitCorrectRequest_notGenerateErrors() throws RepositoryException {
        logInAsCustomer();
        var sessionToken = viewModel.getSessionToken();
        var movieRepository = serviceLocator.getService(MovieRepository.class);
        var movie = movieRepository.getMovie(getMovieListExpected().get(0).getId());
        var cinemaRepository = serviceLocator.getService(CinemaRepository.class);
        var cinema = cinemaRepository.getCinema(getCinemaListExpected(movie).get(0).getId());
        var projectionRepository = serviceLocator.getService(ProjectionRepository.class);
        var projection = projectionRepository.getProjection(getProjectionListExpected(movie,cinema).get(0).getId());
        var request = new SeatListRequest(sessionToken,projection.getId());
        controller.getSeatList(request);
        var expected = getSeatListExpected(projection);
        var actual = viewModel.getSeatList();
        Assertions.assertEquals(expected, actual);
    }

    private List<SeatDto> getSeatListExpected(Projection projection) throws RepositoryException {
        var seatList = serviceLocator.getService(SeatRepository.class).
                getSeatList(projection.getHall());
        return seatList.stream()
                .map(SeatDto::new)//TODO scoprire perchè non funziona
                .collect(Collectors.toList());
    }

 */

}