package com.ttbmp.cinehub.app.repository.seat;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.Seat;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;
import com.ttbmp.cinehub.service.persistence.CinemaDatabase;
import com.ttbmp.cinehub.service.persistence.dao.SeatDao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import java.util.List;
import java.util.stream.Collectors;

public class JdbcSeatRepository implements SeatRepository {

    private SeatDao seatDao = null;

    @Override
    public List<Seat> getSeatList(Hall hall) throws RepositoryException {

        try {
            var seatList = getSeatDao().getSeatList(hall.getId());
            return seatList.stream()
                    .map(seat -> new SeatProxy(seat.getId(), seat.getPosition()))
                    .collect(Collectors.toList());
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }

    }

    @Override
    public Seat getSeat(Ticket ticket) throws RepositoryException {
        try {
            var seat = getSeatDao().getSeatByTicketId(ticket.getId());
            return new SeatProxy(seat.getId(), seat.getPosition());
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    private SeatDao getSeatDao() throws RepositoryException {
        if (seatDao == null) {
            try {
                this.seatDao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getSeatDao();
            } catch (Exception e) {
                throw new RepositoryException(e.getMessage());
            }
        }
        return seatDao;
    }

}
