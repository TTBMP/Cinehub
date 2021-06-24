package com.ttbmp.cinehub.ui.web.buyticket;

import com.ttbmp.cinehub.app.dto.SeatDto;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.service.email.EmailServiceException;
import com.ttbmp.cinehub.app.service.payment.PaymentServiceException;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketPresenter;
import com.ttbmp.cinehub.app.usecase.buyticket.reply.*;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;
import com.ttbmp.cinehub.ui.web.utilities.ErrorHelper;
import org.springframework.ui.Model;

import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * @author Palmieri Ivan
 */
public class BuyTicketPresenterWeb implements BuyTicketPresenter {

    private final Model model;

    public BuyTicketPresenterWeb(Model model) {
        this.model = model;
    }

    @Override
    public void presentMovieList(MovieListReply reply) {
        model.addAttribute("movieList", reply.getMovieList());
    }

    @Override
    public void presentCinemaList(CinemaListReply reply) {
        model.addAttribute("cinemaList", reply.getCinemaList());
    }

    @Override
    public void presentProjectionList(ProjectionListReply reply) {
        model.addAttribute("projectionList", reply.getProjectionDtoList());
    }

    @Override
    public void presentSeatList(SeatListReply reply) {
        var seatList = reply.getSeatDtoList().stream()
                .sorted(Comparator.comparing(SeatDto::getPosition))
                .collect(Collectors.toList());
        model.addAttribute("seatList", seatList);
    }

    @Override
    public void presentTicket(TicketReply reply) {
        model.addAttribute("ticketDetail", reply.getTicketDto());
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
    public void presentSeatAlreadyBookedError(SeatErrorReply reply) {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, reply.getError());
    }

    @Override
    public void presentNullRequest() {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, ErrorHelper.INVALID_ERROR_MESSAGE);
    }

    @Override
    public void presentPaymentServiceException(PaymentServiceException e) {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, e.getMessage());
    }

    @Override
    public void presentSendEmailServiceException(EmailServiceException e) {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, e.getMessage());
    }

}
