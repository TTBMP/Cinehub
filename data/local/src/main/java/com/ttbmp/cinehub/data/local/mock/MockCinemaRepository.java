package com.ttbmp.cinehub.data.local.mock;

import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.entity.Hall;
import com.ttbmp.cinehub.core.entity.Movie;
import com.ttbmp.cinehub.core.entity.Projection;
import com.ttbmp.cinehub.core.repository.CinemaRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class MockCinemaRepository implements CinemaRepository {

    private final List<Cinema> cinemaList = new ArrayList<>();


    @Override
    public List<Cinema> getCinemaByMovie(Movie movie) {

        /*Procedimento che recupera tutte le sale, tutte le proiezioni, e tutti i film dove film = movie*/
        return cinemaList;
    }

    @Override
    public List<Cinema> getAllCinemaList() {
        this.cinemaList.addAll(Arrays.asList(
                new Cinema("The cinema", "Via ovunque", "Tivoli"),
                new Cinema("UCI cinema", "Via delle vie", "Monterotondo"),
                new Cinema("Cinema Giuseppetti", "Via Nettuno", "Nettuno"),
                new Cinema("Cinema Bous", "Via rossi", "Milano")
        ));
        return cinemaList;
    }

    @Override
    public List<Cinema> getCinemaByHall(Hall hall) {
        getAllCinemaList();
        List<Cinema> listCinemaByHall = new ArrayList<>();
        for (Cinema cinema : cinemaList) {
            for (int j = 0; j < cinema.getHallList().size(); j++) {
                if (cinema.getHallList().get(j) == hall) {
                    listCinemaByHall.add(cinema);
                }
            }
        }
        return listCinemaByHall;

    }

    @Override
    public List<Cinema> retriveCinemaByProjection(Projection projection) {
        getAllCinemaList();
        List<Cinema> cinemaListByProjection = new ArrayList<>();
        return null;
    }

    @Override
    public List<Cinema> getCinemaByProjection(List<Projection> projectionList) {
        List<Cinema> cinemaList = new ArrayList<>();
        for (Projection projection : projectionList) {
            cinemaList.add(projection.getCinema());
        }
        return cinemaList;
    }

    public void addCinema(Cinema cinema) {
        this.cinemaList.add(cinema);
    }
}
