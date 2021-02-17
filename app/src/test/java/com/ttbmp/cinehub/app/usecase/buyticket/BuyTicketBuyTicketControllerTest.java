package com.ttbmp.cinehub.app.usecase.buyticket;

import com.ttbmp.cinehub.app.datamapper.MovieDataMapper;
import com.ttbmp.cinehub.app.di.MockServiceLocator;
import com.ttbmp.cinehub.app.repository.movie.MovieRepository;
import com.ttbmp.cinehub.app.service.payment.PaymentServiceException;
import com.ttbmp.cinehub.app.usecase.buyticket.request.*;
import com.ttbmp.cinehub.app.usecase.buyticket.response.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
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
                new GetListMovieRequest(LocalDate.now())
        );
    }

    class MockBuyTicketPresenter implements BuyTicketPresenter {

        @Override
        public void presentMovieApiList(GetListMovieResponse response) {
            try {
                var result = true;
                var movieDtoList = serviceLocator.getService(MovieRepository.class)
                        .getMovieList(String.valueOf(LocalDate.now()));
                var expected = MovieDataMapper.mapToDtoList(movieDtoList);
                var actual = response.getMovieList();
                for(var i = 0; i<expected.size();i++){
                    if (expected.get(i).equals(actual.get(i))){
                        result = false;
                        break;
                    }
                }
                Assertions.assertTrue(result);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void presentCinemaList(GetListCinemaResponse response) {

        }

        @Override
        public void presentCinema(GetCinemaResponse response) {

        }

        @Override
        public void presentSeatList(GetNumberOfSeatsResponse response) {

        }

        @Override
        public void setSelectedTicket(GetTicketBySeatsResponse response) {

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
        public void presentInvalidGetTicketBySeats(GetTicketBySeatsRequest request) {

        }

        @Override
        public void presentGetListCinemaNullRequest() {

        }

        @Override
        public void presentInvalidGetListCinema(GetListCinemaRequest request) {

        }

        @Override
        public void presentGetTimeOfProjectionNullRequest() {

        }

        @Override
        public void presentInvalidGetTimeOfProjection(GetProjectionListRequest request) {

        }

        @Override
        public void presentProjection(GetProjectionResponse request) {

        }

        @Override
        public void presentGetNumberOfSeatsNullRequest() {

        }

        @Override
        public void presentInvalidGetNumberOfSeats(GetNumberOfSeatsRequest request) {

        }

        @Override
        public void presentGetListMovieError() {

        }

        @Override
        public void presentErrorByStripe(PaymentServiceException error) {

        }

        @Override
        public void presentProjectionList(GetProjectionListResponse projectionTimeList) {

        }

        @Override
        public void presentGetListMovieNullRequest() {

        }

        @Override
        public void presentInvalidGetListMovie(GetListMovieRequest request) {

        }

        @Override
        public void presentAuthenticationError() {

        }

        @Override
        public void presentInvalidGetCinema(GetCinemaRequest request) {

        }
    }
}