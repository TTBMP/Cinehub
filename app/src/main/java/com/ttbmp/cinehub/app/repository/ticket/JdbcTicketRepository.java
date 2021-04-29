package com.ttbmp.cinehub.app.repository.ticket;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.movie.MovieProxy;
import com.ttbmp.cinehub.app.repository.seat.SeatProxy;
import com.ttbmp.cinehub.app.repository.seat.SeatRepository;
import com.ttbmp.cinehub.app.repository.user.UserRepository;
import com.ttbmp.cinehub.app.service.movieapi.MovieApiService;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;
import com.ttbmp.cinehub.service.persistence.CinemaDatabase;
import com.ttbmp.cinehub.service.persistence.dao.ProjectionDao;
import com.ttbmp.cinehub.service.persistence.dao.TicketDao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JdbcTicketRepository implements TicketRepository{

    private final ServiceLocator serviceLocator;

    private TicketDao ticketDao = null;

    public JdbcTicketRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public void saveTicket(Ticket ticket, int projectionId) throws RepositoryException {
        try {
            getTicketDao().insert(new com.ttbmp.cinehub.service.persistence.entity.Ticket(ticket.getId(), ticket.getSeat().getId(), projectionId,ticket.getOwner().getId(),ticket.getPrice()));
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public List<Ticket> getTicketList(Projection projection) throws RepositoryException {
        try {
            List<com.ttbmp.cinehub.service.persistence.entity.Ticket> ticketList = getTicketDao().getTicketList(projection.getId());
            return ticketList.stream()
                    .map(ticket -> new TicketProxy(ticket.getId(),ticket.getPrice(),serviceLocator.getService(UserRepository.class),serviceLocator.getService(SeatRepository.class)))
                    .collect(Collectors.toList());
        } catch (DaoMethodException e) {
            return new ArrayList<>();
        }
    }

    private TicketDao getTicketDao() {
        if (ticketDao == null) {
            try {
                this.ticketDao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getTicketDao();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        return ticketDao;
    }
}
