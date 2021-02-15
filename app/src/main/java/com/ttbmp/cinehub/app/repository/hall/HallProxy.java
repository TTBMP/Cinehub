package com.ttbmp.cinehub.app.repository.hall;

import com.ttbmp.cinehub.app.repository.seat.SeatRepository;
import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.Seat;

import java.util.List;

/**
 * @author Fabio Buracchi
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class HallProxy extends Hall {

    private final SeatRepository seatRepository;

    private boolean isSeatListLoaded = false;

    public HallProxy(int id, SeatRepository seatRepository) {
        super(id, null);
        this.seatRepository = seatRepository;
    }

    @Override
    public List<Seat> getSeatList() {
        if (!isSeatListLoaded) {
            setSeatList(seatRepository.getSeatList(this));
        }
        return super.getSeatList();
    }

    @Override
    public void setSeatList(List<Seat> seatList) {
        isSeatListLoaded = true;
        super.setSeatList(seatList);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
