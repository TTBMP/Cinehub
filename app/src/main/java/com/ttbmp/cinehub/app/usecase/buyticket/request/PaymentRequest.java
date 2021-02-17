package com.ttbmp.cinehub.app.usecase.buyticket.request;

import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.dto.MovieDto;
import com.ttbmp.cinehub.app.dto.ProjectionDto;
import com.ttbmp.cinehub.app.dto.TicketDto;
import com.ttbmp.cinehub.app.utilities.request.Request;


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


    public PaymentRequest(TicketDto ticket, ProjectionDto projection, int index, CinemaDto cinemaDto, MovieDto movieDto, String selectedDate) {
        this.index = index;
        this.projection = projection;
        this.ticket = ticket;
        this.cinemaDto = cinemaDto;
        this.movieDto = movieDto;
        this.selectedDate = selectedDate;

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
