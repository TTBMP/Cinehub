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

    private final BuyTicketViewModel viewModel;

    public BuyTicketPresenterFx(BuyTicketViewModel viewModel) {
        this.viewModel = viewModel;
    }


    @Override
    public void presentPayNullRequest() {
        viewModel.errorMessageProperty().setValue("Request can't be null");
    }

    @Override
    public void presentInvalidPayRequest(PaymentRequest request) {
        var error = "";
        if (request.getErrorList().contains(PaymentRequest.CVV_LETTERS_ERROR)) {
            error += PaymentRequest.CVV_LETTERS_ERROR.getMessage();
        }
        if (request.getErrorList().contains(PaymentRequest.NUMBER_OF_CARD_LETTERS_ERROR)) {
            error += PaymentRequest.NUMBER_OF_CARD_LETTERS_ERROR.getMessage();
        }
        if (request.getErrorList().contains(PaymentRequest.CREDIT_CARD_LENGTH_ERROR)) {
            error += PaymentRequest.CREDIT_CARD_LENGTH_ERROR.getMessage();
        }
        if (request.getErrorList().contains(PaymentRequest.MISSING_EMAIL_ERROR)) {
            error += PaymentRequest.MISSING_EMAIL_ERROR.getMessage();
        }
        if (request.getErrorList().contains(PaymentRequest.MISSING_CREDIT_CARD_ERROR)) {
            error += PaymentRequest.MISSING_CREDIT_CARD_ERROR.getMessage();
        }
        if (request.getErrorList().contains(PaymentRequest.LENGTH_CVV_CREDIT_CARD_ERROR)) {
            error += PaymentRequest.LENGTH_CVV_CREDIT_CARD_ERROR.getMessage();
        }
        if (request.getErrorList().contains(PaymentRequest.EXPIRATION_CREDIT_CARD_ERROR)) {
            error += PaymentRequest.EXPIRATION_CREDIT_CARD_ERROR.getMessage();
        }
        if (request.getErrorList().contains(PaymentRequest.EMAIL_ERROR)) {
            error += PaymentRequest.EMAIL_ERROR.getMessage();
        }
        viewModel.errorMessageProperty().setValue(error);
    }


    @Override
    public void presentCinemaListNullRequest() {
        viewModel.errorMessageProperty().setValue("Request can't be null");
    }

    @Override
    public void presentInvalidCinemaListRequest(CinemaListRequest request) {
        viewModel.errorMessageProperty().setValue(CinemaListRequest.MISSING_MOVIE_ERROR.getMessage());
    }

    @Override
    public void presentProjectionListNullRequest() {
        viewModel.errorMessageProperty().setValue("Request can't be null");
    }

    @Override
    public void presentInvalidProjectionListRequest(ProjectionListRequest request) {
        var error = "";
        if (request.getErrorList().contains(ProjectionListRequest.MISSING_MOVIE_ERROR)) {
            error += ProjectionListRequest.MISSING_MOVIE_ERROR.getMessage();
        }
        if (request.getErrorList().contains(ProjectionListRequest.MISSING_DATE_ERROR)) {
            error += ProjectionListRequest.MISSING_DATE_ERROR.getMessage();
        }
        if (request.getErrorList().contains(ProjectionListRequest.MISSING_CINEMA_ERROR)) {
            error += ProjectionListRequest.MISSING_CINEMA_ERROR.getMessage();
        }
        viewModel.errorMessageProperty().setValue(error);
    }

    @Override
    public void presentSeatListNullRequest() {
        viewModel.errorMessageProperty().setValue("Request can't be null");
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
        viewModel.errorMessageProperty().setValue("Request can't be null");
    }

    @Override
    public void presentInvalidMovieListRequest(MovieListRequest request) {
        viewModel.errorMessageProperty().setValue(CinemaListRequest.MISSING_MOVIE_ERROR.getMessage());
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
