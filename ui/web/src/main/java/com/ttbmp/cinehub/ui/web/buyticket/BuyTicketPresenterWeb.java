package com.ttbmp.cinehub.ui.web.buyticket;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.service.payment.PaymentServiceException;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketPresenter;
import com.ttbmp.cinehub.app.usecase.buyticket.request.*;
import com.ttbmp.cinehub.app.usecase.buyticket.response.*;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import org.springframework.ui.Model;

/**
 * @author Palmieri Ivan
 */
public class BuyTicketPresenterWeb implements BuyTicketPresenter {

    private static final String ERROR_MESSAGE_ATTRIBUTE = "messageError";

    private final Model model;

    public BuyTicketPresenterWeb(Model model) {
        this.model = model;
    }

    @Override
    public void presentMovieList(MovieListResponse response) {
        model.addAttribute("movieList", response.getMovieList());
    }

    @Override
    public void presentCinemaList(CinemaListResponse response) {
        model.addAttribute("cinemaList", response.getCinemaList());
    }

    @Override
    public void presentProjectionList(ProjectionListResponse response) {
        model.addAttribute("projectionList", response.getProjectionDtoList());
    }

    @Override
    public void presentSeatList(NumberOfSeatsResponse response) {
        model.addAttribute("seatList", response.getSeatDtoList());
    }

    @Override
    public void presentTicket(TicketResponse response) {
        model.addAttribute("ticketDetail", response.getTicketDto());
    }

    @Override
    public void presentMovieListNullRequest() {
        model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "Movie can't be null");
    }

    @Override
    public void presentInvalidMovieListRequest(MovieListRequest request) {
        model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "Movie can't be null");
    }

    @Override
    public void presentRepositoryError(RepositoryException exception) {
        model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, exception.getMessage());
    }

    @Override
    public void presentCinemaListNullRequest() {
        model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "Cinema can't be null");
    }

    @Override
    public void presentInvalidCinemaListRequest(CinemaListRequest request) {
        model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, request.getErrorList());
    }

    @Override
    public void presentProjectionListNullRequest() {
        model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "Unable to retrieve projection");
    }

    @Override
    public void presentInvalidProjectionListRequest(ProjectionListRequest request) {
        var messageError = "";
        if (request.getErrorList().contains(ProjectionListRequest.MISSING_MOVIE_ERROR)) {
            messageError += (ProjectionListRequest.MISSING_MOVIE_ERROR.getMessage());
        }
        if (request.getErrorList().contains(ProjectionListRequest.MISSING_DATE_ERROR)) {
            messageError += (ProjectionListRequest.MISSING_DATE_ERROR.getMessage());
        }
        if (request.getErrorList().contains(ProjectionListRequest.MISSING_CINEMA_ERROR)) {
            messageError += (ProjectionListRequest.MISSING_CINEMA_ERROR.getMessage());
        }
        model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, messageError);

    }

    @Override
    public void presentSeatListNullRequest() {
        model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "Seat can't be null");
    }

    @Override
    public void presentInvalidSeatListRequest(SeatListRequest request) {
        model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, request.getErrorList());
    }

    @Override
    public void presentUnauthenticatedError(AuthenticatedRequest.UnauthenticatedRequestException exception) {
        model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, exception.getMessage());
    }

    @Override
    public void presentUnauthorizedError(AuthenticatedRequest.UnauthorizedRequestException exception) {
        model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, exception.getMessage());
    }

    @Override
    public void presentSeatAlreadyBookedError(String message) {
        model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, message);
    }

    @Override
    public void presentPayNullRequest() {
        model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "Error with operation Pay");
    }

    @Override
    public void presentInvalidPayRequest(PaymentRequest request) {
        var messageError = "";
        if (request.getErrorList().contains(PaymentRequest.NUMBER_OF_CARD_LETTERS_ERROR)) {
            messageError += (PaymentRequest.NUMBER_OF_CARD_LETTERS_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PaymentRequest.CREDIT_CARD_LENGTH_ERROR)) {
            messageError += (PaymentRequest.CREDIT_CARD_LENGTH_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PaymentRequest.MISSING_EMAIL_ERROR)) {
            messageError += (PaymentRequest.MISSING_EMAIL_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PaymentRequest.MISSING_CREDIT_CARD_ERROR)) {
            messageError += (PaymentRequest.MISSING_CREDIT_CARD_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PaymentRequest.LENGTH_CVV_CREDIT_CARD_ERROR)) {
            messageError += (PaymentRequest.LENGTH_CVV_CREDIT_CARD_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PaymentRequest.EXPIRATION_CREDIT_CARD_ERROR)) {
            messageError += (PaymentRequest.EXPIRATION_CREDIT_CARD_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PaymentRequest.EMAIL_ERROR)) {
            messageError += (PaymentRequest.EMAIL_ERROR.getMessage());
        }
        model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, messageError);
    }

    @Override
    public void presentPayPaymentServiceException(PaymentServiceException exception) {
        model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, exception.getMessage());
    }

}
