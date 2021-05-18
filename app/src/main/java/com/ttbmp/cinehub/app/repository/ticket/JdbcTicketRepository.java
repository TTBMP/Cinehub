package com.ttbmp.cinehub.app.repository.ticket;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.customer.CustomerRepository;
import com.ttbmp.cinehub.app.repository.projection.ProjectionRepository;
import com.ttbmp.cinehub.app.repository.seat.SeatRepository;
import com.ttbmp.cinehub.domain.Customer;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;
import com.ttbmp.cinehub.service.persistence.CinemaDatabase;
import com.ttbmp.cinehub.service.persistence.dao.TicketDao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JdbcTicketRepository implements TicketRepository {

    private final ServiceLocator serviceLocator;

    private TicketDao ticketDao = null;

    public JdbcTicketRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public void saveTicket(Ticket ticket) throws RepositoryException {
        try {
            getTicketDao().insert(new com.ttbmp.cinehub.service.persistence.entity.Ticket(
                    ticket.getId(),
                    ticket.getSeat().getId(),
                    ticket.getProjection().getId(),
                    ticket.getOwner().getId(),
                    ticket.getPrice()
            ));
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public List<Ticket> getTicketList(Customer customer) throws RepositoryException {
        try {
            var ticketList = getTicketDao().getTicketList(customer.getId());
            return ticketList.stream()
                    .map(ticket -> new TicketProxy(
                            ticket.getId(),
                            ticket.getPrice(),
                            serviceLocator.getService(CustomerRepository.class),
                            serviceLocator.getService(SeatRepository.class),
                            serviceLocator.getService(ProjectionRepository.class)
                    ))
                    .collect(Collectors.toList());
        } catch (DaoMethodException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Ticket> getTicketList(Projection projection) throws RepositoryException {
        try {
            var ticketList = getTicketDao().getTicketList(projection.getId());
            return ticketList.stream()
                    .map(ticket -> new TicketProxy(
                            ticket.getId(),
                            ticket.getPrice(),
                            serviceLocator.getService(CustomerRepository.class),
                            serviceLocator.getService(SeatRepository.class),
                            serviceLocator.getService(ProjectionRepository.class)
                    ))
                    .collect(Collectors.toList());
        } catch (DaoMethodException e) {
            return new ArrayList<>();
        }
    }

    private TicketDao getTicketDao() throws RepositoryException {
        if (ticketDao == null) {
            try {
                this.ticketDao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getTicketDao();
            } catch (Exception e) {
                throw new RepositoryException(e.getMessage());
            }
        }
        return ticketDao;
    }

}
