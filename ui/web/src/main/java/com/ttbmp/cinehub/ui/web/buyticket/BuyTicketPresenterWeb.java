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
    public void presentMovieApiList(GetListMovieResponse response) {
        model.addAttribute("movieList", response.getMovieList());
    }

    @Override
    public void presentCinemaList(GetListCinemaResponse response) {
        model.addAttribute("cinemaList", response.getCinemaList());
    }

    @Override
    public void presentCinema(GetCinemaResponse response) {
        model.addAttribute("cinema", response.getCinemaDto());
    }


    @Override
    public void presentSeatList(GetNumberOfSeatsResponse response) {
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
    public void setSelectedTicket(GetTicketBySeatsResponse response) {
        model.addAttribute("selectedTicket", response.getTicketDto());
    }


    @Override
    public void presentPayNullRequest() {
        model.addAttribute(PAYMENT_ERROR_ATTRIBUTE, "Error with operation Pay");

    }

    @Override
    public void presentInvalidPay(PaymentRequest request) {
        if (request.getErrorList().contains(PaymentRequest.MISSING_TICKET_ERROR)) {
            model.addAttribute(PAYMENT_ERROR_ATTRIBUTE, PaymentRequest.MISSING_TICKET_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PaymentRequest.MISSING_PROJECTION_ERROR)) {
            model.addAttribute(PAYMENT_ERROR_ATTRIBUTE, PaymentRequest.MISSING_PROJECTION_ERROR.getMessage());
        }
    }

    @Override
    public void presentGetTicketBySeatsNullRequest() {
        model.addAttribute("ticketError", "Unable to retrieve ticket");
    }

    @Override
    public void presentInvalidGetTicketBySeats(GetTicketBySeatsRequest request) {
        model.addAttribute("ticketError", request.getErrorList());

    }

    @Override
    public void presentGetListCinemaNullRequest() {
        model.addAttribute(CINEMA_ERROR_ATTRIBUTE, "Cinema can't be null");

    }

    @Override
    public void presentInvalidGetListCinema(GetListCinemaRequest request) {
        model.addAttribute(CINEMA_ERROR_ATTRIBUTE, request.getErrorList());


    }

    @Override
    public void presentGetTimeOfProjectionNullRequest() {
        model.addAttribute("projectionError", "Unable to retrieve projection");
    }

    @Override
    public void presentInvalidGetTimeOfProjection(GetProjectionListRequest request) {
        if (request.getErrorList().contains(GetProjectionListRequest.MISSING_MOVIE_ERROR)) {
            model.addAttribute("projectionMovieError", GetProjectionListRequest.MISSING_MOVIE_ERROR.getMessage());
        }
        if (request.getErrorList().contains(GetProjectionListRequest.MISSING_DATE_ERROR)) {
            model.addAttribute("projectionDateError", GetProjectionListRequest.MISSING_DATE_ERROR.getMessage());
        }
    }

    @Override
    public void presentProjection(GetProjectionResponse request) {
        model.addAttribute("projection", request.getProjectionDto());
    }


    @Override
    public void presentGetNumberOfSeatsNullRequest() {
        model.addAttribute("seatsError", "Seat can't be null");
    }

    @Override
    public void presentInvalidGetNumberOfSeats(GetNumberOfSeatsRequest request) {
        model.addAttribute("seatError", request.getErrorList());

    }

    @Override
    public void presentGetListMovieError() {
        model.addAttribute(MOVIE_ERROR_ATTRIBUTE, "Unable to retrieve list of movie");
    }

    @Override
    public void presentErrorByStripe(PaymentServiceException error) {
        model.addAttribute(PAYMENT_ERROR_ATTRIBUTE, error.getMessage());
    }

    @Override
    public void presentProjectionList(GetProjectionListResponse projectionTimeList) {
        model.addAttribute("projectionList", projectionTimeList.getProjectionDto());
    }

    @Override
    public void presentGetListMovieNullRequest() {
        model.addAttribute(MOVIE_ERROR_ATTRIBUTE, "Movie can't be null");
    }

    @Override
    public void presentInvalidGetListMovie(GetListMovieRequest request) {
        model.addAttribute(MOVIE_ERROR_ATTRIBUTE, "Movie can't be null");
    }

    @Override
    public void presentAuthenticationError() {
        model.addAttribute("autenticationError", "Error with the autentication");

    }


    @Override
    public void presentInvalidGetCinema(GetCinemaRequest request) {
        model.addAttribute(CINEMA_ERROR_ATTRIBUTE, "Error with the cinema retriving");

    }

    @Override
    public void presentPayRepositoryException(String message) {

    }

    @Override
    public void presentGetListMovieRepositoryException(String message) {

    }

    @Override
    public void presentGetCinemaListRepositoryException(String message) {

    }

    @Override
    public void presentCreateTicketRepositoryException(String message) {

    }

    @Override
    public void presentGetProjectionListRepositoryException(String message) {

    }

    @Override
    public void presentGetProjectionRepositoryException(String message) {

    }

    @Override
    public void presentGetCinemaRepositoryException(String message) {

    }

}
