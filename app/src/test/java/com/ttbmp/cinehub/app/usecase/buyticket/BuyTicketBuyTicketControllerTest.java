package com.ttbmp.cinehub.app.usecase.buyticket;

import com.ttbmp.cinehub.app.datamapper.MovieDataMapper;
import com.ttbmp.cinehub.app.di.MockServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.movie.MovieRepository;
import com.ttbmp.cinehub.app.service.payment.PaymentServiceException;
import com.ttbmp.cinehub.app.usecase.buyticket.request.*;
import com.ttbmp.cinehub.app.usecase.buyticket.response.*;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


class BuyTicketBuyTicketControllerTest {

    private final MockServiceLocator serviceLocator = new MockServiceLocator();

    @Test
    void getListMovie_whitCorrectRequest_notGenerateErrors() {
        var buyTicketController = new BuyTicketController(serviceLocator, new MockBuyTicketPresenter());
        buyTicketController.getMovieList(new MovieListRequest(LocalDate.now()));
    }

    class MockBuyTicketPresenter implements BuyTicketPresenter {

        @Override
        public void presentMovieList(MovieListResponse response) {
            try {
                var result = true;
                var movieDtoList = serviceLocator.getService(MovieRepository.class)
                        .getMovieList(String.valueOf(LocalDate.now()));
                var expected = MovieDataMapper.mapToDtoList(movieDtoList);
                var actual = response.getMovieList();
                for (var i = 0; i < expected.size(); i++) {
                    if (expected.get(i).equals(actual.get(i))) {
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
        public void presentSeatList(NumberOfSeatsResponse response) {

        }



        @Override
        public void presentPayNullRequest() {

        }

        @Override
        public void presentInvalidPayRequest(PaymentRequest request) {

        }


        @Override
        public void presentCinemaListNullRequest() {

        }

        @Override
        public void presentInvalidCinemaListRequest(CinemaListRequest request) {

        }

        @Override
        public void presentProjectionListNullRequest() {

        }

        @Override
        public void presentInvalidProjectionListRequest(ProjectionListRequest request) {

        }

        @Override
        public void presentSeatListNullRequest() {

        }

        @Override
        public void presentInvalidSeatListRequest(SeatListRequest request) {

        }

        @Override
        public void presentPayPaymentServiceException(PaymentServiceException error) {

        }

        @Override
        public void presentProjectionList(ProjectionListResponse projectionTimeList) {

        }

        @Override
        public void presentMovieListNullRequest() {

        }

        @Override
        public void presentInvalidMovieListRequest(MovieListRequest request) {

        }

        @Override
        public void presentTicket(TicketResponse ticketDto) {

        }

        @Override
        public void presentUnauthenticatedError(AuthenticatedRequest.UnauthenticatedRequestException e) {

        }

        @Override
        public void presentUnauthorizedError(AuthenticatedRequest.UnauthorizedRequestException e) {

        }

        @Override
        public void presentGetSeatListRepositoryException(String e) {

        }

        @Override
        public void presentSeatAlreadyOccupiedException(String s) {

        }

        @Override
        public void presentPayRepositoryException(String message) {

        }

        @Override
        public void presentRepositoryError(RepositoryException message) {

        }

        @Override
        public void presentGetCinemaListRepositoryException(String message) {

        }


        @Override
        public void presentGetProjectionListRepositoryException(String message) {

        }

    }
}