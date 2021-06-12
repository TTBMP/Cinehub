package com.ttbmp.cinehub.app.usecase.buyticket;

import com.ttbmp.cinehub.app.di.MockServiceLocator;
import com.ttbmp.cinehub.app.dto.MovieDto;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.movie.MovieRepository;
import com.ttbmp.cinehub.app.service.payment.PaymentServiceException;
import com.ttbmp.cinehub.app.usecase.buyticket.reply.*;
import com.ttbmp.cinehub.app.usecase.buyticket.request.MovieListRequest;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.stream.Collectors;


class BuyTicketBuyTicketControllerTest {

    private final MockServiceLocator serviceLocator = new MockServiceLocator();

    @Test
    void getListMovie_whitCorrectRequest_notGenerateErrors() {
        var buyTicketController = new BuyTicketController(serviceLocator, new MockBuyTicketPresenter());
        buyTicketController.getMovieList(new MovieListRequest(LocalDate.now()));
    }

    class MockBuyTicketPresenter implements BuyTicketPresenter {

        @Override
        public void presentMovieList(MovieListReply reply) {
            try {
                var result = true;
                var movieList = serviceLocator.getService(MovieRepository.class)
                        .getMovieList(String.valueOf(LocalDate.now()));
                var movieDtoList = movieList.stream()
                        .map(MovieDto::new)
                        .collect(Collectors.toList());
                var expected = movieDtoList;
                var actual = reply.getMovieList();
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
        public void presentCinemaList(CinemaListReply reply) {

        }

        @Override
        public void presentSeatList(SeatListReply reply) {

        }


        @Override
        public void presentNullRequest() {

        }


        @Override
        public void presentInvalidRequest(Request request) {

        }


        @Override
        public void presentPaymentServiceException(PaymentServiceException error) {

        }

        @Override
        public void presentProjectionList(ProjectionListReply reply) {

        }


        @Override
        public void presentTicket(TicketReply reply) {

        }

        @Override
        public void presentUnauthenticatedError(AuthenticatedRequest.UnauthenticatedRequestException e) {

        }

        @Override
        public void presentUnauthorizedError(AuthenticatedRequest.UnauthorizedRequestException e) {

        }

        @Override
        public void presentSeatAlreadyBookedError(SeatErrorReply reply) {

        }

        @Override
        public void presentRepositoryError(RepositoryException message) {

        }

    }
}