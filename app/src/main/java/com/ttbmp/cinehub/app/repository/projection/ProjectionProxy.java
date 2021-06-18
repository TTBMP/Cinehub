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
            if (!isMovieLoaded) {
                setMovie(movieRepository.getMovie(this));
            }
            return super.getMovie();
        } catch (RepositoryException e) {
            throw new LazyLoadingException(e.getMessage());
        }
    }

    @Override
    public void setMovie(Movie movie) {
        isMovieLoaded = true;
        super.setMovie(movie);
    }

    @Override
    public Hall getHall() {
        try {
            if (!isHallLoaded) {
                setHall(hallRepository.getHall(this));
            }
            return super.getHall();
        } catch (RepositoryException e) {
            throw new LazyLoadingException(e.getMessage());
        }
    }

    @Override
    public void setHall(Hall hall) {
        isHallLoaded = true;
        super.setHall(hall);
    }

    @Override
    public Projectionist getProjectionist() {
        if (!isProjectionistLoaded) {
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
        isProjectionistLoaded = true;
        super.setProjectionist(projectionist);
    }

    @Override
    public List<Ticket> getTicketList() {
        try {
            if (!isTicketListLoaded) {
                setTicketList(ticketRepository.getTicketList(this));
            }
            return super.getTicketList();
        } catch (RepositoryException e) {
            throw new LazyLoadingException(e.getMessage());
        }
    }

    @Override
    public void setTicketList(List<Ticket> ticketList) {
        isTicketListLoaded = true;
        super.setTicketList(ticketList);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
