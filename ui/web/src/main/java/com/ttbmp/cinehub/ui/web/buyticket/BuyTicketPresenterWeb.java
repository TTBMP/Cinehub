package com.ttbmp.cinehub.ui.web.buyticket;

import com.ttbmp.cinehub.app.service.payment.PaymentServiceException;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketPresenter;
import com.ttbmp.cinehub.app.usecase.buyticket.request.*;
import com.ttbmp.cinehub.app.usecase.buyticket.response.*;
import org.springframework.ui.Model;

/**
 * @author Palmieri Ivan
 */
public class BuyTicketPresenterWeb implements BuyTicketPresenter {



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
    public void presentSeatList(GetNumberOfSeatsResponse response) {
        model.addAttribute("seatList", response.getSeatDtoList());
        model.addAttribute("sizeSeatList",response.getSeatDtoList().size());
    }

    @Override
    public void setSelectedTicket(GetTicketBySeatsResponse response) {
        model.addAttribute("selectedTicket",response.getTicketDto());
    }


    @Override
    public void presentPayNullRequest() {
        model.addAttribute("paymentError","Error with operation Pay");

    }

    @Override
    public void presentInvalidPay(PaymentRequest request) {
        if (request.getErrorList().contains(PaymentRequest.MISSING_INDEX_ERROR)) {
            model.addAttribute("paymentError",PaymentRequest.MISSING_INDEX_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PaymentRequest.MISSING_TICKET_ERROR)) {
            model.addAttribute("paymentError",PaymentRequest.MISSING_TICKET_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PaymentRequest.MISSING_PROJECTION_ERROR)) {
            model.addAttribute("paymentError",PaymentRequest.MISSING_PROJECTION_ERROR.getMessage());
        }
    }

    @Override
    public void presentGetTicketBySeatsNullRequest() {

    }

    @Override
    public void presentInvalidGetTicketBySeats(GetTicketBySeatsRequest request) {

    }

    @Override
    public void presentGetListCinemaNullRequest() {

    }

    @Override
    public void presentInvalidGetListCinema(GetListCinemaRequest request) {


    }

    @Override
    public void presentGetTimeOfProjectionNullRequest() {


    }

    @Override
    public void presentInvalidGetTimeOfProjection(GetProjectionRequest request) {
        if (request.getErrorList().contains(GetProjectionRequest.MISSING_MOVIE_ERROR)) {
        }
        if (request.getErrorList().contains(GetProjectionRequest.MISSING_DATE_ERROR)) {
        }
    }


    @Override
    public void presentGetNumberOfSeatsNullRequest() {

    }

    @Override
    public void presentInvalidGetNumberOfSeats(GetNumberOfSeatsRequest request) {
    }

    @Override
    public void presentGetListMovieError() {

    }

    @Override
    public void presentErrorByStripe(PaymentServiceException error) {
        model.addAttribute("paymentError",error.getMessage());
    }

    @Override
    public void presentProjectionList(ProjectionListResponse projectionTimeList) {
        model.addAttribute("projectionList",projectionTimeList.getProjectionDto());
    }

    @Override
    public void presentGetListMovieNullRequest() {

    }

    @Override
    public void presentInvalidGetListMovie(GetListMovieRequest request) {
    }

    @Override
    public void presentAutenticationError() {
        
    }

}
