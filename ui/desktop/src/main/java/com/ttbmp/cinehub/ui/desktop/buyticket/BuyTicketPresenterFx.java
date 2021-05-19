package com.ttbmp.cinehub.ui.desktop.buyticket;


import com.ttbmp.cinehub.app.dto.TicketDto;
import com.ttbmp.cinehub.app.service.payment.PaymentServiceException;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketPresenter;
import com.ttbmp.cinehub.app.usecase.buyticket.request.*;
import com.ttbmp.cinehub.app.usecase.buyticket.response.*;
import com.ttbmp.cinehub.app.utilities.request.Request;

/**
 * @author Ivan Palmieri
 */
public class BuyTicketPresenterFx implements BuyTicketPresenter {

    private final BuyTicketViewModel viewModel;

    public BuyTicketPresenterFx(BuyTicketViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentMovieList(MovieListResponse response) {
        viewModel.getMovieList().clear();
        viewModel.getMovieList().addAll(response.getMovieList());
    }

    @Override
    public void presentCinemaList(CinemaListResponse response) {
        viewModel.getCinemaList().setAll(response.getCinemaList());
    }

    @Override
    public void presentSeatList(NumberOfSeatsResponse response) {
        viewModel.getSeatList().setAll(response.getSeatDtoList());
    }


    @Override
    public void presentPayNullRequest() {
        viewModel.paymentErrorProperty().setValue("Error with operation Pay");
    }

    @Override
    public void presentPayInvalidRequest(PaymentRequest request) {
        if (request.getErrorList().contains(PaymentRequest.CVV_LETTERS_ERROR)) {
            viewModel.paymentErrorProperty().setValue(PaymentRequest.CVV_LETTERS_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PaymentRequest.NUMBER_OF_CARD_LETTERS_ERROR)) {
            viewModel.paymentErrorProperty().setValue(PaymentRequest.NUMBER_OF_CARD_LETTERS_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PaymentRequest.CREDIT_CARD_LENGTH_ERROR)) {
            viewModel.paymentErrorProperty().setValue(PaymentRequest.CREDIT_CARD_LENGTH_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PaymentRequest.MISSING_EMAIL_ERROR)) {
            viewModel.paymentErrorProperty().setValue(PaymentRequest.MISSING_EMAIL_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PaymentRequest.MISSING_CREDIT_CARD_ERROR)) {
            viewModel.paymentErrorProperty().setValue(PaymentRequest.MISSING_CREDIT_CARD_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PaymentRequest.LENGTH_CVV_CREDIT_CARD_ERROR)) {
            viewModel.paymentErrorProperty().setValue(PaymentRequest.LENGTH_CVV_CREDIT_CARD_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PaymentRequest.EXPIRATION_CREDIT_CARD_ERROR)) {
            viewModel.paymentErrorProperty().setValue(PaymentRequest.EXPIRATION_CREDIT_CARD_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PaymentRequest.EMAIL_ERROR)) {
            viewModel.paymentErrorProperty().setValue(PaymentRequest.EMAIL_ERROR.getMessage());
        }
    }


    @Override
    public void presentCinemaListNullRequest() {
        viewModel.cinemaErrorProperty().setValue("Error with operation GetListCinema");
    }

    @Override
    public void presentCinemaListInvalidRequest(CinemaListRequest request) {
        viewModel.cinemaErrorProperty().setValue(CinemaListRequest.MISSING_MOVIE_ERROR.getMessage());
    }

    @Override
    public void presentProjectionListNullRequest() {
        viewModel.cinemaErrorProperty().setValue("Error with operation GetTimeOfProjection");
    }

    @Override
    public void presentProjectionListInvalidRequest(ProjectionListRequest request) {
        if (request.getErrorList().contains(ProjectionListRequest.MISSING_MOVIE_ERROR)) {
            viewModel.cinemaErrorProperty().setValue(ProjectionListRequest.MISSING_MOVIE_ERROR.getMessage());
        }
        if (request.getErrorList().contains(ProjectionListRequest.MISSING_DATE_ERROR)) {
            viewModel.cinemaErrorProperty().setValue(ProjectionListRequest.MISSING_DATE_ERROR.getMessage());
        }
        if (request.getErrorList().contains(ProjectionListRequest.MISSING_CINEMA_ERROR)) {
            viewModel.cinemaErrorProperty().setValue(ProjectionListRequest.MISSING_CINEMA_ERROR.getMessage());
        }
    }

    @Override
    public void presentSeatListNullRequest() {
        viewModel.seatErrorProperty().setValue("Error with operation GetNumberOfSeats");
    }

    @Override
    public void presentSeatListInvalidRequest(SeatListRequest request) {
    }


    @Override
    public void presentErrorByStripe(PaymentServiceException error) {
        viewModel.paymentErrorProperty().setValue(error.getMessage());
    }

    @Override
    public void presentProjectionList(ProjectionListResponse response) {
        viewModel.getProjectionOfProjectionTimeList().setAll(response.getProjectionDtoList());
    }

    @Override
    public void presentMovieListNullRequest() {
        viewModel.movieErrorProperty().setValue("Unable to recover movies by service");
    }

    @Override
    public void presentMovieListInvalidRequest(MovieListRequest request) {
        viewModel.movieErrorProperty().setValue(CinemaListRequest.MISSING_MOVIE_ERROR.getMessage());
    }

    @Override
    public void presentAuthenticationError() {
        viewModel.authenticationErrorProperty().setValue("Problems with accessing the server ");
    }

    @Override
    public void presentTicket(TicketDto ticketDto) {
        viewModel.selectedTicketProperty().setValue(ticketDto);
    }

    @Override
    public void presentPayRepositoryException(String message) {
        viewModel.payRepositoryExceptionProperty().setValue(message);
    }

    @Override
    public void presentMovieListRepositoryException(String message) {
        viewModel.listMovieRepositoryExceptionProperty().setValue(message);
    }

    @Override
    public void presentCinemaListRepositoryException(String message) {
        viewModel.cinemaListRepositoryExceptionProperty().setValue(message);
    }



    @Override
    public void presentProjectionListRepositoryException(String message) {
        viewModel.projectionListRepositoryExceptionProperty().setValue(message);

    }

}
