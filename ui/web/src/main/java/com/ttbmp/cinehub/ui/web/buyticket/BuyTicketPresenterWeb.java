package com.ttbmp.cinehub.ui.web.buyticket;

import com.ttbmp.cinehub.app.dto.ProjectionDto;
import com.ttbmp.cinehub.app.service.payment.PaymentServiceException;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketPresenter;
import com.ttbmp.cinehub.app.usecase.buyticket.request.*;
import com.ttbmp.cinehub.app.usecase.buyticket.response.*;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class BuyTicketPresenterWeb implements BuyTicketPresenter {

    private static final String PAYMENT_ERROR_ATTRIBUTE = "paymentError";
    private static final String MOVIE_ERROR_ATTRIBUTE = "movieError";
    private static final String CINEMA_ERROR_ATTRIBUTE = "cinemaError";
    private final Model model;


    public BuyTicketPresenterWeb(Model model) {
        this.model = model;
    }

    @Override
    public void presentMovieApiList(MovieListResponse response) {
        model.addAttribute("movieList", response.getMovieList());
    }

    @Override
    public void presentCinemaList(CinemaListResponse response) {
        model.addAttribute("cinemaList", response.getCinemaList());
    }

    @Override
    public void presentCinema(CinemaResponse response) {
        model.addAttribute("cinema", response.getCinemaDto());
    }


    @Override
    public void presentSeatList(NumberOfSeatsResponse response) {
        model.addAttribute("seatList", response.getSeatDtoList());
        var selectedProjection = ((ProjectionDto) model.getAttribute("projection"));
        model.addAttribute("valList", getSeatsNameList(selectedProjection, response.getSeatDtoList().size()));
    }

    private List<String> getSeatsNameList(ProjectionDto selectedProjection, int size) {
        List<String> valList = new ArrayList<>();
        for (var i = 0; i < size; i++) {
            valList.add(getName(selectedProjection, i));
        }
        return valList;
    }

    private String getName(ProjectionDto projection, int seatNumber) {
        var position = projection.getHallDto().getSeatList().get(seatNumber).getPosition();
        for (var ticket : projection.getListTicket()) {
            if (ticket.getSeatDto().getPosition().equals(position)) {
                return "SOLD";
            }
        }
        return position;
    }

    @Override
    public void setSelectedTicket(TicketResponse response) {
        model.addAttribute("selectedTicket", response.getTicketDto());
    }


    @Override
    public void presentPayNullRequest() {
        model.addAttribute(PAYMENT_ERROR_ATTRIBUTE, "Error with operation Pay");

    }

    @Override
    public void presentInvalidPay(PaymentRequest request) {
        if (request.getErrorList().contains(PaymentRequest.MISSING_TICKET_ERROR)) {
            model.addAttribute(PaymentRequest.MISSING_TICKET_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PaymentRequest.MISSING_PROJECTION_ERROR)) {
            model.addAttribute(PaymentRequest.MISSING_PROJECTION_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PaymentRequest.MISSING_CINEMA_ERROR)) {
            model.addAttribute(PaymentRequest.MISSING_CINEMA_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PaymentRequest.MISSING_CREDIT_CARD_ERROR)) {
            model.addAttribute(PaymentRequest.MISSING_CREDIT_CARD_ERROR.getMessage());
        }

        if (request.getErrorList().contains(PaymentRequest.MISSING_EMAIL_ERROR)) {
            model.addAttribute(PaymentRequest.MISSING_EMAIL_ERROR.getMessage());
        }
    }

    @Override
    public void presentGetTicketBySeatsNullRequest() {
        model.addAttribute("ticketError", "Unable to retrieve ticket");
    }

    @Override
    public void presentInvalidGetTicketBySeats(TicketRequest request) {
        if (request.getErrorList().contains(TicketRequest.MISSING_LIST_SEATS_ERROR)) {
            model.addAttribute(TicketRequest.MISSING_LIST_SEATS_ERROR.getMessage());
        }

        if (request.getErrorList().contains(TicketRequest.MISSING_OPTION_ONE_ERROR)) {
            model.addAttribute(TicketRequest.MISSING_OPTION_ONE_ERROR.getMessage());
        }
        if (request.getErrorList().contains(TicketRequest.MISSING_OPTION_TWO_ERROR)) {
            model.addAttribute(TicketRequest.MISSING_OPTION_TWO_ERROR.getMessage());
        }
        if (request.getErrorList().contains(TicketRequest.MISSING_OPTION_THREE_ERROR)) {
            model.addAttribute(TicketRequest.MISSING_OPTION_THREE_ERROR.getMessage());
        }
        if (request.getErrorList().contains(TicketRequest.MISSING_PROJECTION_ERROR)) {
            model.addAttribute(TicketRequest.MISSING_PROJECTION_ERROR.getMessage());
        }
        if (request.getErrorList().contains(TicketRequest.MISSING_NUMBER_ERROR)) {
            model.addAttribute(TicketRequest.MISSING_NUMBER_ERROR.getMessage());
        }
    }

    @Override
    public void presentGetListCinemaNullRequest() {
        model.addAttribute(CINEMA_ERROR_ATTRIBUTE, "Cinema can't be null");

    }

    @Override
    public void presentInvalidGetListCinema(CinemaListRequest request) {
        model.addAttribute(CINEMA_ERROR_ATTRIBUTE, request.getErrorList());


    }

    @Override
    public void presentGetTimeOfProjectionNullRequest() {
        model.addAttribute("projectionError", "Unable to retrieve projection");
    }

    @Override
    public void presentInvalidGetTimeOfProjection(ProjectionListRequest request) {
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
    public void presentProjection(ProjectionResponse response) {
        model.addAttribute("projection", response.getProjectionDto());
        model.addAttribute("price", response.getProjectionDto().getBasePrice());
    }


    @Override
    public void presentGetNumberOfSeatsNullRequest() {
        model.addAttribute("seatsError", "Seat can't be null");
    }

    @Override
    public void presentInvalidGetNumberOfSeats(CinemaInformationRequest request) {
        model.addAttribute("seatError", request.getErrorList());

    }

    @Override
    public void presentErrorByStripe(PaymentServiceException error) {
        model.addAttribute(PAYMENT_ERROR_ATTRIBUTE, error.getMessage());
    }

    @Override
    public void presentProjectionList(ProjectionListResponse projectionTimeList) {
        model.addAttribute("projectionList", projectionTimeList.getProjectionDtoList());
    }

    @Override
    public void presentGetListMovieNullRequest() {
        model.addAttribute(MOVIE_ERROR_ATTRIBUTE, "Movie can't be null");
    }

    @Override
    public void presentInvalidGetListMovie(MovieListRequest request) {
        model.addAttribute(MOVIE_ERROR_ATTRIBUTE, "Movie can't be null");
    }

    @Override
    public void presentAuthenticationError() {
        model.addAttribute("autenticationError", "Error with the autentication");

    }


    @Override
    public void presentInvalidGetCinema(CinemaInformationRequest request) {
        model.addAttribute(CINEMA_ERROR_ATTRIBUTE, "Error with the cinema retriving");

    }

    @Override
    public void presentPayRepositoryException(String message) {
        model.addAttribute("payRepositoryException", message);
    }

    @Override
    public void presentGetListMovieRepositoryException(String message) {
        model.addAttribute("listMovieRepositoryException", message);

    }

    @Override
    public void presentGetCinemaListRepositoryException(String message) {
        model.addAttribute("cinemaListRepositoryException", message);

    }

    @Override
    public void presentCreateTicketRepositoryException(String message) {
        model.addAttribute("ticketRepositoryException", message);

    }

    @Override
    public void presentGetProjectionListRepositoryException(String message) {
        model.addAttribute("projectionListRepositoryException", message);

    }

    @Override
    public void presentGetProjectionRepositoryException(String message) {
        model.addAttribute("projectionRepositoryException", message);

    }

    @Override
    public void presentGetCinemaRepositoryException(String message) {
        model.addAttribute("cinemaRepositoryException", message);

    }

}
