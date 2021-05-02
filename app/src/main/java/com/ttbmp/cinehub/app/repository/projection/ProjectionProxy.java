package com.ttbmp.cinehub.app.repository.projection;

import com.ttbmp.cinehub.app.repository.employee.projectionist.ProjectionistRepository;
import com.ttbmp.cinehub.app.repository.hall.HallRepository;
import com.ttbmp.cinehub.app.repository.movie.MovieRepository;
import com.ttbmp.cinehub.app.repository.ticket.TicketRepository;
import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.Movie;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.employee.Projectionist;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;

import java.util.List;

/**
 * @author Fabio Buracchi
 */
public class ProjectionProxy extends Projection {

    private final MovieRepository movieRepository;
    private final HallRepository hallRepository;
    private final ProjectionistRepository projectionistRepository;
    private final TicketRepository ticketRepository;

    private boolean isMovieLoaded = false;
    private boolean isHallLoaded = false;
    private boolean isProjectionistLoaded = false;
    private boolean isTicketListLoaded = false;

    public ProjectionProxy(
            int id,
            String date,
            String startTime,
            MovieRepository movieRepository,
            HallRepository hallRepository,
            ProjectionistRepository projectionistRepository,
            TicketRepository ticketRepository,
            long basePrice
    ) {
        super(id, date, startTime, null, null, null, null,basePrice);
        this.movieRepository = movieRepository;
        this.hallRepository = hallRepository;
        this.projectionistRepository = projectionistRepository;
        this.ticketRepository = ticketRepository;

    }



    @Override
    public Movie getMovie() {
        if (!isMovieLoaded) {
            setMovie(movieRepository.getMovie(this));
        }
        return super.getMovie();
    }

    @Override
    public void setMovie(Movie movie) {
        isMovieLoaded = true;
        super.setMovie(movie);
    }

    @Override
    public Hall getHall() {
        if (!isHallLoaded) {
            setHall(hallRepository.getHall(this));
        }
        return super.getHall();
    }

    @Override
    public void setHall(Hall hall) {
        isHallLoaded = true;
        super.setHall(hall);
    }


    @Override
    public Projectionist getProjectionist() {
        if (!isProjectionistLoaded) {
            setProjectionist(projectionistRepository.getProjectionist(this));
        }
        return super.getProjectionist();
    }

    @Override
    public void setProjectionist(Projectionist projectionist) {
        isProjectionistLoaded = true;
        super.setProjectionist(projectionist);
    }

    @Override
    public List<Ticket> getTicketList() {
        if (!isTicketListLoaded) {
            setTicketList(ticketRepository.getTicketList(this));
        }
        return super.getTicketList();
    }

    @Override
    public void setTicketList(List<Ticket> ticketList) {
        isTicketListLoaded = true;
        super.setTicketList(ticketList);
    }

}
