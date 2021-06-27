package com.ttbmp.cinehub.app.usecase.buyticket;

import com.ttbmp.cinehub.app.di.MockServiceLocator;
import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.dto.MovieDto;
import com.ttbmp.cinehub.app.dto.ProjectionDto;
import com.ttbmp.cinehub.app.dto.SeatDto;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.cinema.CinemaRepository;
import com.ttbmp.cinehub.app.repository.movie.MovieRepository;
import com.ttbmp.cinehub.app.repository.projection.ProjectionRepository;
import com.ttbmp.cinehub.app.repository.seat.SeatRepository;
import com.ttbmp.cinehub.app.usecase.buyticket.request.CinemaListRequest;
import com.ttbmp.cinehub.app.usecase.buyticket.request.MovieListRequest;
import com.ttbmp.cinehub.app.usecase.buyticket.request.ProjectionListRequest;
import com.ttbmp.cinehub.app.usecase.buyticket.request.SeatListRequest;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.Movie;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.Seat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ivan Palmieri
 */
class BuyTicketBuyTicketControllerTest {

    private MockServiceLocator serviceLocator;
    private MockBuyTicketViewModel viewModel;
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

    @Test
    void getListMovie_whitCorrectRequest_notGenerateErrors() throws RepositoryException {
        var date = LocalDate.now();
        var request = new MovieListRequest(date);
        controller.getMovieList(request);
        var expected = getMovieListExpected(date);
        var actual = viewModel.getMovieList();
        Assertions.assertArrayEquals(
                expected.stream().map(MovieDto::getId).toArray(),
                actual.stream().map(MovieDto::getId).toArray()
        );
    }

    @Test
    void getListMovie_whitNullDate_generateErrors() {
        var request = new MovieListRequest(null);
        Assertions.assertDoesNotThrow(() -> controller.getMovieList(request));
    }

    @Test
    void getListCinema_whitCorrectRequest_notGenerateErrors() throws RepositoryException {
        var movieRepository = serviceLocator.getService(MovieRepository.class);
        var date = LocalDate.now();
        var movieList = getMovieListExpected(date);
        if (movieList.size() != 0) {
            var movie = movieRepository.getMovie(movieList.get(0).getId());
            var requestCinema = new CinemaListRequest(movie.getId(), String.valueOf(date));
            controller.getCinemaList(requestCinema);
            var expected = getCinemaListExpected(movie, String.valueOf(date));
            var actual = viewModel.getCinemaList();
            Assertions.assertArrayEquals(
                    expected.stream().map(CinemaDto::getId).toArray(),
                    actual.stream().map(CinemaDto::getId).toArray()
            );
        }
    }

    @Test
    void getListCinema_whitNullDate_generateErrors() throws RepositoryException {
        var movieRepository = serviceLocator.getService(MovieRepository.class);
        var date = LocalDate.now();
        var movieList = getMovieListExpected(date);
        if (movieList.size() != 0) {
            var id = movieRepository.getMovie(movieList.get(0).getId()).getId();
            var requestCinema = new CinemaListRequest(id, null);
            Assertions.assertDoesNotThrow(() -> controller.getCinemaList(requestCinema));
        } else {
            var requestCinema = new CinemaListRequest(null, null);
            Assertions.assertDoesNotThrow(() -> controller.getCinemaList(requestCinema));
        }
    }

    @Test
    void getListProjection_whitCorrectRequest_notGenerateErrors() throws RepositoryException {

        var movieRepository = serviceLocator.getService(MovieRepository.class);
        var date = LocalDate.now();
        var movieList = getMovieListExpected(date);
        if (movieList.size() != 0) {
            var movie = movieRepository.getMovie(movieList.get(0).getId());
            var cinemaRepository = serviceLocator.getService(CinemaRepository.class);
            var cinema = cinemaRepository.getCinema(getCinemaListExpected(movie, String.valueOf(date)).get(0).getId());
            var request = new ProjectionListRequest(movie.getId(), cinema.getId(), date.toString());
            controller.getProjectionList(request);
            var expected = getProjectionListExpected(movie, cinema, date);
            var actual = viewModel.getProjectionList();
            Assertions.assertArrayEquals(
                    expected.stream().map(ProjectionDto::getId).toArray(),
                    actual.stream().map(ProjectionDto::getId).toArray()
            );
        }
    }

    @Test
    void getListSeat_whitCorrectRequest_notGenerateErrors() throws RepositoryException {
        logInAsCustomer();
        var sessionToken = viewModel.getSessionToken();
        var movieRepository = serviceLocator.getService(MovieRepository.class);
        var date = LocalDate.now();
        var movieList = getMovieListExpected(date);
        if (movieList.size() != 0) {
            var movie = movieRepository.getMovie(movieList.get(0).getId());
            var cinemaRepository = serviceLocator.getService(CinemaRepository.class);
            var cinema = cinemaRepository.getCinema(getCinemaListExpected(movie, String.valueOf(date)).get(0).getId());
            var projectionRepository = serviceLocator.getService(ProjectionRepository.class);
            var projection = projectionRepository.getProjection(getProjectionListExpected(movie, cinema, date).get(0).getId());
            var request = new SeatListRequest(sessionToken, projection.getId());
            controller.getSeatList(request);
            var expected = getSeatListExpected(projection);
            var actual = viewModel.getSeatList();
            Assertions.assertArrayEquals(
                    expected.stream().map(SeatDto::getId).toArray(),
                    actual.stream().map(SeatDto::getId).toArray()
            );
        }
    }

    private void logInAsCustomer() {
        viewModel.setSessionToken("5KClU7hbNgedJAwLuF9eFVl6Qzz2");
    }

    private List<SeatDto> getSeatListExpected(Projection projection) throws RepositoryException {
        var seatList = serviceLocator.getService(SeatRepository.class).
                getSeatList(projection.getHall());
        return seatList.stream()
                .map((Seat seat) -> new SeatDto(seat, false))
                .collect(Collectors.toList());
    }

    private List<ProjectionDto> getProjectionListExpected(Movie movie, Cinema cinema, LocalDate date) throws RepositoryException {
        return serviceLocator.getService(ProjectionRepository.class).getProjectionList(cinema, movie, String.valueOf(date)).stream()
                .map(ProjectionDto::new)
                .collect(Collectors.toList());
    }

    private List<CinemaDto> getCinemaListExpected(Movie movie, String date) throws RepositoryException {
        return serviceLocator.getService(CinemaRepository.class).getListCinema(movie, date).stream()
                .map(CinemaDto::new)
                .collect(Collectors.toList());
    }

    private List<MovieDto> getMovieListExpected(LocalDate date) throws RepositoryException {
        return serviceLocator.getService(MovieRepository.class).getMovieList(String.valueOf(date)).stream()
                .map(MovieDto::new)
                .collect(Collectors.toList());
    }

}
