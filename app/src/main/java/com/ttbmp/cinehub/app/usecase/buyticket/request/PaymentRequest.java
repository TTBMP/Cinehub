package com.ttbmp.cinehub.app.usecase.buyticket.request;

import com.ttbmp.cinehub.app.dto.*;
import com.ttbmp.cinehub.app.usecase.Request;


/**
 * @author Ivan Palmieri
 */
public class PaymentRequest extends Request {

    public static final Request.Error MISSING_TICKET_ERROR = new Request.Error("Ticket can't be null");
    public static final Request.Error MISSING_PROJECTION_ERROR = new Request.Error("Projection can't be null");

    private final Integer index;
    private ProjectionDto projection;
    private TicketDto ticket;
    private CinemaDto cinemaDto;
    private MovieDto movieDto;
    private String selectedDate;
    private CreditCardDto creditCard;

    public PaymentRequest(TicketDto ticket, ProjectionDto projection, int index, CinemaDto cinemaDto, MovieDto movieDto, String selectedDate, CreditCardDto creditCard) {
        this.index = index;
        this.projection = projection;
        this.ticket = ticket;
        this.cinemaDto = cinemaDto;
        this.movieDto = movieDto;
        this.selectedDate = selectedDate;
        this.creditCard = creditCard;
    }

    public MovieDto getMovieDto() {
        return movieDto;
    }

    public void setMovieDto(MovieDto movieDto) {
        this.movieDto = movieDto;
    }

    public String getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
    }

    public CinemaDto getCinemaDto() {
        return cinemaDto;
    }

    public void setCinemaDto(CinemaDto cinemaDto) {
        this.cinemaDto = cinemaDto;
    }

    public ProjectionDto getProjection() {
        return projection;
    }

    public void setProjection(ProjectionDto projection) {
        this.projection = projection;
    }

    public Integer getIndex() {
        return index;
    }

    public TicketDto getTicket() {
        return ticket;
    }

    public void setTicket(TicketDto ticket) {
        this.ticket = ticket;
    }

    public CreditCardDto getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCardDto creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public void onValidate() {
        if (projection == null) {
            addError(MISSING_PROJECTION_ERROR);

        }
        if (ticket == null) {
            addError(MISSING_TICKET_ERROR);
        }
    }
}
