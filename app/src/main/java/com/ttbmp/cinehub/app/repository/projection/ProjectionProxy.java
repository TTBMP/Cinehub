package com.ttbmp.cinehub.app.repository.projection;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.LazyLoadingException;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.employee.projectionist.ProjectionistRepository;
import com.ttbmp.cinehub.app.repository.hall.HallRepository;
import com.ttbmp.cinehub.app.repository.movie.MovieRepository;
import com.ttbmp.cinehub.app.repository.ticket.TicketRepository;
import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.Movie;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.employee.Projectionist;
import com.ttbmp.cinehub.domain.ticket.Ticket;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Fabio Buracchi
 */
@EqualsAndHashCode(callSuper = true)
public class ProjectionProxy extends Projection {

    private final MovieRepository movieRepository;
    private final HallRepository hallRepository;
    private final ProjectionistRepository projectionistRepository;
    private final TicketRepository ticketRepository;

    private boolean isProjectionMovieLoaded = false;
    private boolean isProjectionHallLoaded = false;
    private boolean isProjectionProjectionistLoaded = false;
    private boolean isProjectionTicketListLoaded = false;

    public ProjectionProxy(ServiceLocator serviceLocator, int id, String date, String startTime, long basePrice) {
        super(id, date, startTime, null, null, null, null, basePrice);
        this.movieRepository = serviceLocator.getService(MovieRepository.class);
        this.hallRepository = serviceLocator.getService(HallRepository.class);
        this.projectionistRepository = serviceLocator.getService(ProjectionistRepository.class);
        this.ticketRepository = serviceLocator.getService(TicketRepository.class);
    }

    @Override
    public Movie getMovie() {
        try {
            if (!isProjectionMovieLoaded) {
                setMovie(movieRepository.getMovie(this));
            }
            return super.getMovie();
        } catch (RepositoryException e) {
            throw new LazyLoadingException(e.getMessage());
        }
    }

    @Override
    public void setMovie(Movie movie) {
        isProjectionMovieLoaded = true;
        super.setMovie(movie);
    }

    @Override
    public Hall getHall() {
        try {
            if (!isProjectionHallLoaded) {
                setHall(hallRepository.getHall(this));
            }
            return super.getHall();
        } catch (RepositoryException e) {
            throw new LazyLoadingException(e.getMessage());
        }
    }

    @Override
    public void setHall(Hall hall) {
        isProjectionHallLoaded = true;
        super.setHall(hall);
    }

    @Override
    public Projectionist getProjectionist() {
        if (!isProjectionProjectionistLoaded) {
            try {
                setProjectionist(projectionistRepository.getProjectionist(this));
            } catch (RepositoryException e) {
                throw new LazyLoadingException(e.getMessage());
            }
        }
        return super.getProjectionist();
    }

    @Override
    public void setProjectionist(Projectionist projectionist) {
        isProjectionProjectionistLoaded = true;
        super.setProjectionist(projectionist);
    }

    @Override
    public List<Ticket> getTicketList() {
        try {
            if (!isProjectionTicketListLoaded) {
                setTicketList(ticketRepository.getTicketList(this));
            }
            return super.getTicketList();
        } catch (RepositoryException e) {
            throw new LazyLoadingException(e.getMessage());
        }
    }

    @Override
    public void setTicketList(List<Ticket> ticketList) {
        isProjectionTicketListLoaded = true;
        super.setTicketList(ticketList);
    }

}
