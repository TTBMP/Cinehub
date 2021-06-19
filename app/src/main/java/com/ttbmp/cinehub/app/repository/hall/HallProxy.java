package com.ttbmp.cinehub.app.repository.hall;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.LazyLoadingException;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.seat.SeatRepository;
import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.Seat;

import java.util.List;

/**
 * @author Fabio Buracchi
 */
public class HallProxy extends Hall {

    private final SeatRepository seatRepository;

    private boolean isSeatListLoaded = false;

    public HallProxy(ServiceLocator serviceLocator, int id, String name) {
        super(id, null, name);
        this.seatRepository = serviceLocator.getService(SeatRepository.class);
    }

    @Override
    public List<Seat> getSeatList() {
        try {
            if (!isSeatListLoaded) {
                setSeatList(seatRepository.getSeatList(this));
            }
            return super.getSeatList();
        } catch (RepositoryException e) {
            throw new LazyLoadingException(e.getMessage());
        }
    }

    @Override
    public void setSeatList(List<Seat> seatList) {
        isSeatListLoaded = true;
        super.setSeatList(seatList);
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
