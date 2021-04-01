package com.ttbmp.cinehub.app.usecase.buyticket;

import com.ttbmp.cinehub.app.datamapper.MovieDataMapper;
import com.ttbmp.cinehub.app.di.MockServiceLocator;
import com.ttbmp.cinehub.app.dto.MovieDto;
import com.ttbmp.cinehub.app.repository.movie.MovieRepository;
import com.ttbmp.cinehub.app.service.payment.PaymentServiceException;
import com.ttbmp.cinehub.app.usecase.buyticket.request.*;
import com.ttbmp.cinehub.app.usecase.buyticket.response.*;
import com.ttbmp.cinehub.domain.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


class BuyTicketControllerTest {

    private final MockServiceLocator serviceLocator = new MockServiceLocator();

    @Test
    void getListMovie_whitCorrectRequest_notGenerateErrors() {
        BuyTicketController buyTicketController = new BuyTicketController(
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
                boolean result= true;
                List<Movie> movieDtoList = serviceLocator.getService(MovieRepository.class)
                        .getMovieList(
                                String.valueOf(LocalDate.now())
                        );
                List<MovieDto> expected = MovieDataMapper.mapToDtoList(movieDtoList);
                List<MovieDto> actual = response.getMovieList();
                for(int i=0;i<expected.size();i++){
                    if (expected.get(i).getId() != actual.get(i).getId()) {
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
        public void presentInvalidGetTimeOfProjection(GetProjectionRequest request) {

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
        public void presentProjectionList(ProjectionListResponse projectionTimeList) {

        }

        @Override
        public void presentGetListMovieNullRequest() {

        }

        @Override
        public void presentInvalidGetListMovie(GetListMovieRequest request) {

        }

        @Override
        public void presentAutenticationError() {

        }
    }
}