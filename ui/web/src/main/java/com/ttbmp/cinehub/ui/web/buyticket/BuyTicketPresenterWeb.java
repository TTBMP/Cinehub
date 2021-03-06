package com.ttbmp.cinehub.ui.web.buyticket;

import com.ttbmp.cinehub.app.dto.SeatDto;
import com.ttbmp.cinehub.app.service.email.EmailServiceException;
import com.ttbmp.cinehub.app.service.payment.PaymentServiceException;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketPresenter;
import com.ttbmp.cinehub.app.usecase.buyticket.reply.*;
import com.ttbmp.cinehub.ui.web.utilities.ErrorHelper;
import com.ttbmp.cinehub.ui.web.utilities.PresenterWeb;
import org.springframework.ui.Model;

import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * @author Palmieri Ivan
 */
public class BuyTicketPresenterWeb extends PresenterWeb implements BuyTicketPresenter {

    public BuyTicketPresenterWeb(Model model) {
        super(model);
    }

    @Override
    public void presentGetMovieList(MovieListReply reply) {
        model.addAttribute("movieList", reply.getMovieList());
    }

    @Override
    public void presentGetCinemaList(CinemaListReply reply) {
        model.addAttribute("cinemaList", reply.getCinemaList());
    }

    @Override
    public void presentGetProjectionList(ProjectionListReply reply) {
        model.addAttribute("projectionList", reply.getProjectionDtoList());
    }

    @Override
    public void presentGetSeatList(SeatListReply reply) {
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
    public void presentSeatAlreadyBookedError(SeatErrorReply reply) {
        model.addAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME, reply.getError());
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
