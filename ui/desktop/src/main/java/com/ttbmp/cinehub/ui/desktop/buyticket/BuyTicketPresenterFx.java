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
public class BuyTicketPresenterFx implements BuyTicketPresenter {

    private static final String NULL_REQUEST_MESSAGE = "Request can't be null";

    private final BuyTicketViewModel viewModel;

    public BuyTicketPresenterFx(BuyTicketViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentPayNullRequest() {
        viewModel.errorMessageProperty().setValue(NULL_REQUEST_MESSAGE);
    }

    @Override
    public void presentInvalidPayRequest(PaymentRequest request) {
        var error = "";
        if (request.getErrorList().contains(PaymentRequest.CVV_LETTERS_ERROR)) {
            error += " "+PaymentRequest.CVV_LETTERS_ERROR.getMessage();
        }
        if (request.getErrorList().contains(PaymentRequest.NUMBER_OF_CARD_LETTERS_ERROR)) {
            error += " "+PaymentRequest.NUMBER_OF_CARD_LETTERS_ERROR.getMessage();
        }
        if (request.getErrorList().contains(PaymentRequest.CREDIT_CARD_LENGTH_ERROR)) {
            error += " "+PaymentRequest.CREDIT_CARD_LENGTH_ERROR.getMessage();
        }
        if (request.getErrorList().contains(PaymentRequest.MISSING_EMAIL_ERROR)) {
            error += " "+PaymentRequest.MISSING_EMAIL_ERROR.getMessage();
        }
        if (request.getErrorList().contains(PaymentRequest.MISSING_CREDIT_CARD_ERROR)) {
            error += " "+PaymentRequest.MISSING_CREDIT_CARD_ERROR.getMessage();
        }
        if (request.getErrorList().contains(PaymentRequest.LENGTH_CVV_CREDIT_CARD_ERROR)) {
            error += " "+PaymentRequest.LENGTH_CVV_CREDIT_CARD_ERROR.getMessage();
        }
        if (request.getErrorList().contains(PaymentRequest.EXPIRATION_CREDIT_CARD_ERROR)) {
            error += " "+PaymentRequest.EXPIRATION_CREDIT_CARD_ERROR.getMessage();
        }
        if (request.getErrorList().contains(PaymentRequest.EMAIL_ERROR)) {
            error += " "+PaymentRequest.EMAIL_ERROR.getMessage();
        }
        if (request.getErrorList().contains(PaymentRequest.MISSING_CUSTOMER_ERROR)) {
            error += " "+PaymentRequest.MISSING_CUSTOMER_ERROR.getMessage();
        }
        if (request.getErrorList().contains(PaymentRequest.MISSING_PROJECTION_ERROR)) {
            error += " "+PaymentRequest.MISSING_PROJECTION_ERROR.getMessage();
        }
        if (request.getErrorList().contains(PaymentRequest.MISSING_SEAT_ERROR)) {
            error += " "+PaymentRequest.MISSING_SEAT_ERROR.getMessage();
        }
        viewModel.errorMessageProperty().setValue(error);
    }


    @Override
    public void presentCinemaListNullRequest() {
        viewModel.errorMessageProperty().setValue(NULL_REQUEST_MESSAGE);
    }

    @Override
    public void presentInvalidCinemaListRequest(CinemaListRequest request) {
        var error = "";
        if (request.getErrorList().contains(CinemaListRequest.INVALID_MOVIE)) {
            error += " "+CinemaListRequest.INVALID_MOVIE.getMessage();
        }
        if (request.getErrorList().contains(CinemaListRequest.MISSING_DATE_ERROR)) {
            error += " "+CinemaListRequest.MISSING_DATE_ERROR.getMessage();
        }
        viewModel.errorMessageProperty().setValue(error);
    }

    @Override
    public void presentProjectionListNullRequest() {
        viewModel.errorMessageProperty().setValue(NULL_REQUEST_MESSAGE);
    }

    @Override
    public void presentInvalidProjectionListRequest(ProjectionListRequest request) {
        var error = "";
        if (request.getErrorList().contains(ProjectionListRequest.INVALID_MOVIE)) {
            error += " "+ProjectionListRequest.INVALID_MOVIE.getMessage();
        }
        if (request.getErrorList().contains(ProjectionListRequest.INVALID_CINEMA)) {
            error += " "+ProjectionListRequest.INVALID_CINEMA.getMessage();
        }
        viewModel.errorMessageProperty().setValue(error);
    }

    @Override
    public void presentSeatListNullRequest() {
        viewModel.errorMessageProperty().setValue(NULL_REQUEST_MESSAGE);
    }

    @Override
    public void presentInvalidSeatListRequest(SeatListRequest request) {
        var error = "";
        if (request.getErrorList().contains(SeatListRequest.PROJECTION_ERROR)) {
            error += " "+SeatListRequest.PROJECTION_ERROR.getMessage();
        }
        viewModel.errorMessageProperty().setValue(error);
    }


    @Override
    public void presentPaymentServiceException(PaymentServiceException exception) {
        viewModel.errorMessageProperty().setValue(exception.getMessage());
    }


    @Override
    public void presentMovieListNullRequest() {
        viewModel.errorMessageProperty().setValue(NULL_REQUEST_MESSAGE);
    }

    @Override
    public void presentInvalidMovieListRequest(MovieListRequest request) {
        var error = "";
        if (request.getErrorList().contains(MovieListRequest.MISSING_DATE_ERROR)) {
            error += " "+MovieListRequest.MISSING_DATE_ERROR.getMessage();
        }
        viewModel.errorMessageProperty().setValue(error);
    }


    @Override
    public void presentUnauthenticatedError(AuthenticatedRequest.UnauthenticatedRequestException exception) {
        viewModel.errorMessageProperty().setValue(exception.getMessage() + ", you must log in first");

    }

    @Override
    public void presentUnauthorizedError(AuthenticatedRequest.UnauthorizedRequestException exception) {
        viewModel.errorMessageProperty().setValue(exception.getMessage());
    }

    @Override
    public void presentSeatAlreadyBookedError(SeatErrorResponse response) {
        viewModel.errorMessageProperty().setValue(response.getError());
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
    public void presentSeatList(SeatListResponse response) {
        viewModel.seatListProperty().setAll(response.getSeatDtoList());
    }


}
