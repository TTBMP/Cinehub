package com.ttbmp.cinehub.app.repository.seat;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.utilities.repository.JdbcRepository;
import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.Seat;
import com.ttbmp.cinehub.domain.ticket.Ticket;
import com.ttbmp.cinehub.service.persistence.dao.SeatDao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import java.util.List;
import java.util.stream.Collectors;

public class JdbcSeatRepository extends JdbcRepository implements SeatRepository {

    public JdbcSeatRepository(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public Seat getSeat(int id) throws RepositoryException {
        try {
            var seat = getDao(SeatDao.class).getSeat(id);
            return new SeatProxy(getServiceLocator(), seat.getId(), seat.getPosition());
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public Seat getSeat(Ticket ticket) throws RepositoryException {
        try {
            var seat = getDao(SeatDao.class).getSeatByTicketId(ticket.getId());
            return new SeatProxy(getServiceLocator(), seat.getId(), seat.getPosition());
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public List<Seat> getSeatList(Hall hall) throws RepositoryException {
        try {
            var seatList = getDao(SeatDao.class).getSeatList(hall.getId());
            return seatList.stream()
                    .map(seat -> new SeatProxy(getServiceLocator(), seat.getId(), seat.getPosition()))
                    .collect(Collectors.toList());
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

}
