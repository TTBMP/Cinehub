package com.ttbmp.cinehub.ui.web.buyticket;

import com.ttbmp.cinehub.app.dto.TicketDto;
import com.ttbmp.cinehub.app.service.payment.PaymentServiceException;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketPresenter;
import com.ttbmp.cinehub.app.usecase.buyticket.request.*;
import com.ttbmp.cinehub.app.usecase.buyticket.response.*;
import org.springframework.ui.Model;

/**
 * @author Palmieri Ivan
 */
public class BuyTicketPresenterWeb implements BuyTicketPresenter {

    private static final String MOVIE_ERROR_ATTRIBUTE = "movieError";
    private static final String CINEMA_ERROR_ATTRIBUTE = "cinemaError";
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
    public void presentProjectionList(ProjectionListResponse projectionTimeList) {
        model.addAttribute("projectionList", projectionTimeList.getProjectionDtoList());
    }

    @Override
    public void presentSeatList(NumberOfSeatsResponse response) {
        model.addAttribute("seatList", response.getSeatDtoList());
    }


    @Override
    public void presentPayNullRequest() {
        model.addAttribute("paymentError", "Error with operation Pay");

    }

    @Override
    public void presentPayInvalidRequest(PaymentRequest request) {
        if (request.getErrorList().contains(PaymentRequest.NUMBER_OF_CARD_LETTERS_ERROR)) {
            model.addAttribute("paymentError",PaymentRequest.NUMBER_OF_CARD_LETTERS_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PaymentRequest.CREDIT_CARD_LENGTH_ERROR)) {
            model.addAttribute("paymentError",PaymentRequest.CREDIT_CARD_LENGTH_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PaymentRequest.MISSING_EMAIL_ERROR)) {
            model.addAttribute("paymentError",PaymentRequest.MISSING_EMAIL_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PaymentRequest.MISSING_CREDIT_CARD_ERROR)) {
            model.addAttribute("paymentError",PaymentRequest.MISSING_CREDIT_CARD_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PaymentRequest.LENGTH_CVV_CREDIT_CARD_ERROR)) {
            model.addAttribute("paymentError",PaymentRequest.LENGTH_CVV_CREDIT_CARD_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PaymentRequest.EXPIRATION_CREDIT_CARD_ERROR)) {
            model.addAttribute("paymentError",PaymentRequest.EXPIRATION_CREDIT_CARD_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PaymentRequest.EMAIL_ERROR)) {
            model.addAttribute("paymentError",PaymentRequest.EMAIL_ERROR.getMessage());
        }
    }

    @Override
    public void presentCinemaListNullRequest() {
        model.addAttribute(CINEMA_ERROR_ATTRIBUTE, "Cinema can't be null");

    }

    @Override
    public void presentCinemaListInvalidRequest(CinemaListRequest request) {
        model.addAttribute(CINEMA_ERROR_ATTRIBUTE, request.getErrorList());


    }

    @Override
    public void presentProjectionListNullRequest() {
        model.addAttribute("projectionError", "Unable to retrieve projection");
    }

    @Override
    public void presentProjectionListInvalidRequest(ProjectionListRequest request) {
        if (request.getErrorList().contains(ProjectionListRequest.MISSING_MOVIE_ERROR)) {
            model.addAttribute(ProjectionListRequest.MISSING_MOVIE_ERROR.getMessage());
        }
        if (request.getErrorList().contains(ProjectionListRequest.MISSING_DATE_ERROR)) {
            model.addAttribute(ProjectionListRequest.MISSING_DATE_ERROR.getMessage());
        }
        if (request.getErrorList().contains(ProjectionListRequest.MISSING_CINEMA_ERROR)) {
            model.addAttribute(ProjectionListRequest.MISSING_CINEMA_ERROR.getMessage());
        }
    }

    @Override
    public void presentSeatListNullRequest() {
        model.addAttribute("seatsError", "Seat can't be null");
    }

    @Override
    public void presentSeatListInvalidRequest(SeatListRequest request) {
        model.addAttribute("seatError", request.getErrorList());

    }

    @Override
    public void presentErrorByStripe(PaymentServiceException error) {
        model.addAttribute("paymentError", error.getMessage());
    }

    @Override
    public void presentMovieListNullRequest() {
        model.addAttribute(MOVIE_ERROR_ATTRIBUTE, "Movie can't be null");
    }

    @Override
    public void presentMovieListInvalidRequest(MovieListRequest request) {
        model.addAttribute(MOVIE_ERROR_ATTRIBUTE, "Movie can't be null");
    }

    @Override
    public void presentAuthenticationError() {
        model.addAttribute("autenticationError", "Error with the autentication");

    }

    @Override
    public void presentTicket(TicketDto ticketDto) {
        model.addAttribute("ticketDetail", ticketDto);
    }

    @Override
    public void presentPayRepositoryException(String message) {
        model.addAttribute("paymentError", message);
    }

    @Override
    public void presentMovieListRepositoryException(String message) {
        model.addAttribute("listMovieRepositoryException", message);

    }

    @Override
    public void presentCinemaListRepositoryException(String message) {
        model.addAttribute("cinemaListRepositoryException", message);

    }


    @Override
    public void presentProjectionListRepositoryException(String message) {
        model.addAttribute("projectionListRepositoryException", message);

    }

}
