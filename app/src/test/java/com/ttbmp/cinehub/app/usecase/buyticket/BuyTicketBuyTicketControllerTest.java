package com.ttbmp.cinehub.app.usecase.buyticket;

import com.ttbmp.cinehub.app.datamapper.MovieDataMapper;
import com.ttbmp.cinehub.app.di.MockServiceLocator;
import com.ttbmp.cinehub.app.dto.TicketDto;
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
        public void presentPayInvalidRequest(PaymentRequest request) {

        }


        @Override
        public void presentCinemaListNullRequest() {

        }

        @Override
        public void presentCinemaListInvalidRequest(CinemaListRequest request) {

        }

        @Override
        public void presentProjectionListNullRequest() {

        }

        @Override
        public void presentProjectionListInvalidRequest(ProjectionListRequest request) {

        }

        @Override
        public void presentSeatListNullRequest() {

        }

        @Override
        public void presentSeatListInvalidRequest(SeatListRequest request) {

        }

        @Override
        public void presentErrorByStripe(PaymentServiceException error) {

        }

        @Override
        public void presentProjectionList(ProjectionListResponse projectionTimeList) {

        }

        @Override
        public void presentMovieListNullRequest() {

        }

        @Override
        public void presentMovieListInvalidRequest(MovieListRequest request) {

        }

        @Override
        public void presentAuthenticationError() {

        }

        @Override
        public void presentTicket(TicketDto ticketDto) {

        }

        @Override
        public void presentPayRepositoryException(String message) {

        }

        @Override
        public void presentMovieListRepositoryException(String message) {

        }

        @Override
        public void presentCinemaListRepositoryException(String message) {

        }


        @Override
        public void presentProjectionListRepositoryException(String message) {

        }

    }
}