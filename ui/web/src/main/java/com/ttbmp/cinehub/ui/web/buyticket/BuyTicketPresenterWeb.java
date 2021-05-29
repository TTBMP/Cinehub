package com.ttbmp.cinehub.ui.web.buyticket;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.service.payment.PaymentServiceException;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketPresenter;
import com.ttbmp.cinehub.app.usecase.buyticket.response.*;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;
import com.ttbmp.cinehub.ui.web.utilities.ErrorHelper;
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
    public void presentSeatList(SeatListResponse response) {
        model.addAttribute("seatList", response.getSeatDtoList());
    }

    @Override
    public void presentTicket(TicketResponse response) {
        model.addAttribute("ticketDetail", response.getTicketDto());
    }


    @Override
    public void presentRepositoryError(RepositoryException e) {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, e.getMessage());
    }


    @Override
    public void presentInvalidRequest(Request request) {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, ErrorHelper.getRequestErrorMessage(request));
    }


    @Override
    public void presentUnauthenticatedError(AuthenticatedRequest.UnauthenticatedRequestException e) {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, e.getMessage());
    }

    @Override
    public void presentUnauthorizedError(AuthenticatedRequest.UnauthorizedRequestException e) {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, e.getMessage());
    }

    @Override
    public void presentSeatAlreadyBookedError(SeatErrorResponse response) {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, response.getError());
    }

    @Override
    public void presentNullRequest() {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, ErrorHelper.INVALID_ERROR_MESSAGE);
    }

    @Override
    public void presentPaymentServiceException(PaymentServiceException e) {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, e.getMessage());
    }

}
