package com.ttbmp.cinehub.data.local.mock;

import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.entity.Hall;
import com.ttbmp.cinehub.core.entity.Seat;
import com.ttbmp.cinehub.core.repository.HallRepository;

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

    public Hall getHall(int id) {
        return hallList.get(id);
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
