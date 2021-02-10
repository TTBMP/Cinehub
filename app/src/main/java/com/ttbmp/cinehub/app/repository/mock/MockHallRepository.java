package com.ttbmp.cinehub.app.repository.mock;

import com.ttbmp.cinehub.app.repository.HallRepository;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.Seat;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Massimo Mazzetti
 */
public class MockHallRepository implements HallRepository {

    private static final List<Hall> hallList = new ArrayList<>();

    static {
        boolean status = true;
        for (int i = 0; i < 14; i++) {
            List<Seat> seatList = new ArrayList<>();
            for (int j = 0; j < 50 + i; j++) {
                seatList.add(new Seat(5L, status));
                status = !status;
            }
            hallList.add(new Hall(i, seatList));
        }
    }

    @Override
    public List<Hall> getCinemaHallList(Cinema cinema) {
        return cinema.getHallList();
    }

    public List<Hall> getAllHall() {
        return hallList;
    }

    public List<Hall> getHallList(int cinemaId) {
        return hallList.subList(7 * cinemaId, 7 + cinemaId * 7);
    }
}
