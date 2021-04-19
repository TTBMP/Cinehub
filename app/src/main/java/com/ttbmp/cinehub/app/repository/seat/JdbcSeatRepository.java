package com.ttbmp.cinehub.app.repository.seat;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.Seat;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;
import com.ttbmp.cinehub.service.persistence.CinemaDatabase;
import com.ttbmp.cinehub.service.persistence.dao.SeatDao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

public class JdbcSeatRepository implements SeatRepository{

    private final ServiceLocator serviceLocator;

    private SeatDao seatDao = null;

    public JdbcSeatRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public List<Seat> getSeatList(Hall hall) throws RepositoryException {

        try {
            List<com.ttbmp.cinehub.service.persistence.entity.Seat> seatList = getSeatDao().getSeatList(hall.getId());
            return seatList.stream()
                    .map(seat -> new SeatProxy(seat.getId(),seat.getPrice(),seat.getState(),seat.getPosition()))
                    .collect(Collectors.toList());
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }

    }

    @Override
    public Seat getSeat(Ticket ticket) throws RepositoryException {

        try {
            com.ttbmp.cinehub.service.persistence.entity.Seat seat = getSeatDao().getSeatByTicketId(ticket.getId());
            return new SeatProxy(seat.getId(),seat.getPrice(),seat.getState(),seat.getPosition());

        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }


    private SeatDao getSeatDao() {
        if (seatDao == null) {
            try {
                this.seatDao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getSeatDao();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return seatDao;
    }
}
