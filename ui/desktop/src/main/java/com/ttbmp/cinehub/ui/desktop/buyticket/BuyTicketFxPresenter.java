package com.ttbmp.cinehub.ui.desktop.buyticket;


import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.service.payment.PaymentServiceException;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketPresenter;
import com.ttbmp.cinehub.app.usecase.buyticket.request.*;
import com.ttbmp.cinehub.app.usecase.buyticket.response.*;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;

/**
 * @author Ivan Palmieri
 */
public class BuyTicketFxPresenter implements BuyTicketPresenter {

    private final BuyTicketViewModel viewModel;

    public BuyTicketFxPresenter(BuyTicketViewModel viewModel) {
        this.viewModel = viewModel;
    }


    @Override
    public void presentPayNullRequest() {
        viewModel.errorMessageProperty().setValue("Pay null request");
    }

    @Override
    public void presentInvalidPayRequest(PaymentRequest request) {
        var message = "";
        if (request.getErrorList().contains(PaymentRequest.CVV_LETTERS_ERROR)) {
           message += PaymentRequest.CVV_LETTERS_ERROR.getMessage();
        }
        if (request.getErrorList().contains(PaymentRequest.NUMBER_OF_CARD_LETTERS_ERROR)) {
           message += PaymentRequest.NUMBER_OF_CARD_LETTERS_ERROR.getMessage();
        }
        if (request.getErrorList().contains(PaymentRequest.CREDIT_CARD_LENGTH_ERROR)) {
            message += PaymentRequest.CREDIT_CARD_LENGTH_ERROR.getMessage();
        }
        if (request.getErrorList().contains(PaymentRequest.MISSING_EMAIL_ERROR)) {
            message += PaymentRequest.MISSING_EMAIL_ERROR.getMessage();
        }
        if (request.getErrorList().contains(PaymentRequest.MISSING_CREDIT_CARD_ERROR)) {
            message += PaymentRequest.MISSING_CREDIT_CARD_ERROR.getMessage();
        }
        if (request.getErrorList().contains(PaymentRequest.LENGTH_CVV_CREDIT_CARD_ERROR)) {
            message += PaymentRequest.LENGTH_CVV_CREDIT_CARD_ERROR.getMessage();
        }
        if (request.getErrorList().contains(PaymentRequest.EXPIRATION_CREDIT_CARD_ERROR)) {
            message += PaymentRequest.EXPIRATION_CREDIT_CARD_ERROR.getMessage();
        }
        if (request.getErrorList().contains(PaymentRequest.EMAIL_ERROR)) {
            message += PaymentRequest.EMAIL_ERROR.getMessage();
        }
        viewModel.errorMessageProperty().setValue(message);
    }


    @Override
    public void presentCinemaListNullRequest() {
        viewModel.errorMessageProperty().setValue("Cinema list null request");
    }

    @Override
    public void presentInvalidCinemaListRequest(CinemaListRequest request) {
        viewModel.errorMessageProperty().setValue(CinemaListRequest.MISSING_MOVIE_ERROR.getMessage());
    }

    @Override
    public void presentProjectionListNullRequest() {
        viewModel.errorMessageProperty().setValue("Projection list null request");
    }

    @Override
    public void presentInvalidProjectionListRequest(ProjectionListRequest request) {
        var message = "";
        if (request.getErrorList().contains(ProjectionListRequest.MISSING_MOVIE_ERROR)) {
            message += ProjectionListRequest.MISSING_MOVIE_ERROR.getMessage();
        }
        if (request.getErrorList().contains(ProjectionListRequest.MISSING_DATE_ERROR)) {
            message += ProjectionListRequest.MISSING_DATE_ERROR.getMessage();
        }
        if (request.getErrorList().contains(ProjectionListRequest.MISSING_CINEMA_ERROR)) {
            message +=  ProjectionListRequest.MISSING_CINEMA_ERROR.getMessage();
        }
        viewModel.errorMessageProperty().setValue(message);
    }

    @Override
    public void presentSeatListNullRequest() {
        viewModel.errorMessageProperty().setValue("Seat list null request");
    }

    @Override
    public void presentInvalidSeatListRequest(SeatListRequest request) {
        viewModel.errorMessageProperty().setValue(SeatListRequest.PROJECTION_ERROR.getMessage());
    }


    @Override
    public void presentPayPaymentServiceException(PaymentServiceException exception) {
        viewModel.errorMessageProperty().setValue(exception.getMessage());
    }


    @Override
    public void presentMovieListNullRequest() {
        viewModel.errorMessageProperty().setValue("Unable to recover movies by service");
    }

    @Override
    public void presentInvalidMovieListRequest(MovieListRequest request) {
        viewModel.errorMessageProperty().setValue(CinemaListRequest.MISSING_MOVIE_ERROR.getMessage());
    }


    @Override
    public void presentUnauthenticatedError(AuthenticatedRequest.UnauthenticatedRequestException exception) {
        viewModel.errorMessageProperty().setValue(exception.getMessage()+", you must log in first ...");

    }

    @Override
    public void presentUnauthorizedError(AuthenticatedRequest.UnauthorizedRequestException exception) {
        viewModel.errorMessageProperty().setValue(exception.getMessage());
    }

    @Override
    public void presentSeatAlreadyOccupiedException(String exception) {
        viewModel.errorMessageProperty().setValue(exception);
    }

    @Override
    public void presentRepositoryError(RepositoryException exception) {
        viewModel.errorMessageProperty().setValue(exception.getMessage());
    }

    @Override
    public void presentTicket(TicketResponse response) {
        viewModel.selectedTicketProperty().setValue(response.getTicketDto());
    }

    @Override
    public void presentProjectionList(ProjectionListResponse response) {
        viewModel.projectionTimeListProperty().setAll(response.getProjectionDtoList());
    }

    @Override
    public void presentMovieList(MovieListResponse response) {
        viewModel.movieListProperty().clear();
        viewModel.movieListProperty().addAll(response.getMovieList());
    }

    @Override
    public void presentCinemaList(CinemaListResponse response) {
        viewModel.cinemaListProperty().setAll(response.getCinemaList());
    }

    @Override
    public void presentSeatList(NumberOfSeatsResponse response) {
        viewModel.seatListProperty().setAll(response.getSeatDtoList());
    }





}
