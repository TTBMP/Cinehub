package com.ttbmp.cinehub.app.repository.hall;

import com.ttbmp.cinehub.app.repository.cinema.CinemaRepository;
import com.ttbmp.cinehub.app.repository.cinema.MockCinemaRepository;
import com.ttbmp.cinehub.app.repository.projection.MockProjectionRepository;
import com.ttbmp.cinehub.app.repository.projection.ProjectionRepository;
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
    public List<Hall> getCinemaHallList(int cinemaId) {
        Cinema cinema = new MockCinemaRepository().getCinema(cinemaId);

        return cinema.getHallList();
    }

    public List<Hall> getAllHall() {
        return hallList;
    }

    public List<Hall> getHallList(int cinemaId) {
        return hallList.subList(7 * cinemaId, 7 + cinemaId * 7);
    }
}
