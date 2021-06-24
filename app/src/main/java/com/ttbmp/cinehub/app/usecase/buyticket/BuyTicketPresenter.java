package com.ttbmp.cinehub.app.usecase.buyticket;

import com.ttbmp.cinehub.app.service.email.EmailServiceException;
import com.ttbmp.cinehub.app.service.payment.PaymentServiceException;
import com.ttbmp.cinehub.app.service.security.SecurePresenter;
import com.ttbmp.cinehub.app.usecase.buyticket.reply.*;

/**
 * @author Ivan Palmieri
 */
public interface BuyTicketPresenter extends SecurePresenter {

    void presentMovieList(MovieListReply reply);

    void presentCinemaList(CinemaListReply reply);

    void presentProjectionList(ProjectionListReply reply);

    void presentSeatList(SeatListReply reply);

    void presentTicket(TicketReply reply);

    void presentSeatAlreadyBookedError(SeatErrorReply reply);

    void presentPaymentServiceException(PaymentServiceException exception);

    void presentSendEmailServiceException(EmailServiceException e);
}
