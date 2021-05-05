package com.ttbmp.cinehub.app.usecase.buyticket;

import com.ttbmp.cinehub.app.datamapper.MovieDataMapper;
import com.ttbmp.cinehub.app.di.MockServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.movie.MovieRepository;
import com.ttbmp.cinehub.app.service.payment.PaymentServiceException;
import com.ttbmp.cinehub.app.usecase.buyticket.request.*;
import com.ttbmp.cinehub.app.usecase.buyticket.response.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


class BuyTicketBuyTicketControllerTest {

    private final MockServiceLocator serviceLocator = new MockServiceLocator();

    @Test
    void getListMovie_whitCorrectRequest_notGenerateErrors() {
        var buyTicketController = new BuyTicketController(
                serviceLocator,
                new MockBuyTicketPresenter()
        );
        buyTicketController.getListMovie(
                new MovieListRequest(LocalDate.now())
        );
    }

    class MockBuyTicketPresenter implements BuyTicketPresenter {

        @Override
        public void presentMovieApiList(MovieListResponse response) {
            try {
                var result = true;
                var movieDtoList = serviceLocator.getService(MovieRepository.class)
                        .getMovieList(
                                String.valueOf(LocalDate.now())
                        );
                var expected = MovieDataMapper.mapToDtoList(movieDtoList);
                var actual = response.getMovieList();
                for (var i = 0; i < expected.size(); i++) {
                    if (expected.get(i).getId() != actual.get(i).getId()) {
                        result = false;
                        break;
                    }
                }
                Assertions.assertTrue(result);
            } catch (RepositoryException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void presentCinemaList(CinemaListResponse response) {

        }

        @Override
        public void presentCinema(CinemaResponse response) {

        }

        @Override
        public void presentSeatList(NumberOfSeatsResponse response) {

        }

        @Override
        public void setSelectedTicket(TicketResponse response) {

        }

        @Override
        public void presentPayNullRequest() {

        }

        @Override
        public void presentInvalidPay(PaymentRequest request) {

        }

        @Override
        public void presentGetTicketBySeatsNullRequest() {

        }

        @Override
        public void presentInvalidGetTicketBySeats(TicketRequest request) {

        }

        @Override
        public void presentGetListCinemaNullRequest() {

        }

        @Override
        public void presentInvalidGetListCinema(CinemaListRequest request) {

        }

        @Override
        public void presentGetTimeOfProjectionNullRequest() {

        }

        @Override
        public void presentInvalidGetTimeOfProjection(ProjectionListRequest request) {

        }

        @Override
        public void presentProjection(ProjectionResponse request) {

        }

        @Override
        public void presentGetNumberOfSeatsNullRequest() {

        }

        @Override
        public void presentInvalidGetNumberOfSeats(CinemaInformationRequest request) {

        }

        @Override
        public void presentGetListMovieError() {

        }

        @Override
        public void presentErrorByStripe(PaymentServiceException error) {

        }

        @Override
        public void presentProjectionList(ProjectionListResponse projectionTimeList) {

        }

        @Override
        public void presentGetListMovieNullRequest() {

        }

        @Override
        public void presentInvalidGetListMovie(MovieListRequest request) {

        }

        @Override
        public void presentAuthenticationError() {

        }


        @Override
        public void presentInvalidGetCinema(CinemaInformationRequest request) {

        }

        @Override
        public void presentPayRepositoryException(String message) {

        }

        @Override
        public void presentGetListMovieRepositoryException(String message) {

        }

        @Override
        public void presentGetCinemaListRepositoryException(String message) {

        }

        @Override
        public void presentCreateTicketRepositoryException(String message) {

        }

        @Override
        public void presentGetProjectionListRepositoryException(String message) {

        }

        @Override
        public void presentGetProjectionRepositoryException(String message) {

        }

        @Override
        public void presentGetCinemaRepositoryException(String message) {

        }
    }
}