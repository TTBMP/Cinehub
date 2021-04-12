package com.ttbmp.cinehub.ui.web.buyticket;

import com.ttbmp.cinehub.app.dto.ProjectionDto;
import com.ttbmp.cinehub.app.dto.TicketDto;
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

    private final Model model;
    private String paymentError = "paymentError";
    private String movieError = "movieError";


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
    public void presentSeatList(GetNumberOfSeatsResponse response) {
        model.addAttribute("seatList", response.getSeatDtoList());
        ProjectionDto selectedProjection = ((ArrayList<ProjectionDto>) model.getAttribute("projectionList")).get(0);
        model.addAttribute("valList", getSeatsNameList(selectedProjection,response.getSeatDtoList().size()));
    }

    private List<String> getSeatsNameList(ProjectionDto selectedProjection, int size) {
        List<String> valList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            valList.add(getName(selectedProjection, i));
        }
        return valList;
    }

    private String getName(ProjectionDto projection, int seatNumber) {
        String position = projection.getHallDto().getSeatList().get(seatNumber).getPosition();
        for (TicketDto ticket : projection.getListTicket()) {
            if (ticket.getSeatDto().getPosition().equals(position)) {
                return "SOLD";
            }
        }
        return position;
    }

    @Override
    public void setSelectedTicket(GetTicketBySeatsResponse response) {
        model.addAttribute("selectedTicket",response.getTicketDto());
    }


    @Override
    public void presentPayNullRequest() {
        model.addAttribute(paymentError,"Error with operation Pay");

    }

    @Override
    public void presentInvalidPay(PaymentRequest request) {
        if (request.getErrorList().contains(PaymentRequest.MISSING_INDEX_ERROR)) {
            model.addAttribute(paymentError,PaymentRequest.MISSING_INDEX_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PaymentRequest.MISSING_TICKET_ERROR)) {
            model.addAttribute(paymentError,PaymentRequest.MISSING_TICKET_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PaymentRequest.MISSING_PROJECTION_ERROR)) {
            model.addAttribute(paymentError,PaymentRequest.MISSING_PROJECTION_ERROR.getMessage());
        }
    }

    @Override
    public void presentGetTicketBySeatsNullRequest() {
        model.addAttribute("ticketError","Unable to retrieve ticket");
    }

    @Override
    public void presentInvalidGetTicketBySeats(GetTicketBySeatsRequest request) {
        model.addAttribute("ticketError",request.getErrorList());

    }

    @Override
    public void presentGetListCinemaNullRequest() {
        model.addAttribute("cinemaError","Cinema can't be null");

    }

    @Override
    public void presentInvalidGetListCinema(GetListCinemaRequest request) {
        model.addAttribute("cinemaError",request.getErrorList());


    }

    @Override
    public void presentGetTimeOfProjectionNullRequest() {
        model.addAttribute("projectionError","Unable to retrieve projection");
    }

    @Override
    public void presentInvalidGetTimeOfProjection(GetProjectionRequest request) {
        if (request.getErrorList().contains(GetProjectionRequest.MISSING_MOVIE_ERROR)) {
            model.addAttribute("projectionMovieError",GetProjectionRequest.MISSING_MOVIE_ERROR.getMessage());
        }
        if (request.getErrorList().contains(GetProjectionRequest.MISSING_DATE_ERROR)) {
            model.addAttribute("projectionDateError",GetProjectionRequest.MISSING_DATE_ERROR.getMessage());
        }
    }


    @Override
    public void presentGetNumberOfSeatsNullRequest() {
        model.addAttribute("seatsError","Seat can't be null");
    }

    @Override
    public void presentInvalidGetNumberOfSeats(GetNumberOfSeatsRequest request) {
        model.addAttribute("seatError",request.getErrorList());

    }

    @Override
    public void presentGetListMovieError() {
        model.addAttribute(movieError,"Unable to retrieve list of movie");
    }

    @Override
    public void presentErrorByStripe(PaymentServiceException error) {
        model.addAttribute(paymentError,error.getMessage());
    }

    @Override
    public void presentProjectionList(ProjectionListResponse projectionTimeList) {
        model.addAttribute("projectionList",projectionTimeList.getProjectionDto());
    }

    @Override
    public void presentGetListMovieNullRequest() {
        model.addAttribute(movieError,"Movie can't be null");
    }

    @Override
    public void presentInvalidGetListMovie(GetListMovieRequest request) {
        model.addAttribute(movieError,"Movie can't be null");
    }

    @Override
    public void presentAutenticationError() {
        model.addAttribute("autenticationError","Error with the autentication");

    }

}
