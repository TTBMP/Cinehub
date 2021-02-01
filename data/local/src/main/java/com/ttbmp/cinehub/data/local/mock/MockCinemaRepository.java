package com.ttbmp.cinehub.data.local.mock;

import com.ttbmp.cinehub.core.entity.*;
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
    public List<Cinema> getAllCinemaList() {
        cinemaList.clear();
        Cinema cinema1 = new Cinema("The cinema", "Via ovunque", "Tivoli");
        List<Hall> hallList = new ArrayList<>();
        Hall hall1 = new Hall(1);
        List<Projection> projectionList = new ArrayList<>();
        Projection projection = new Projection(
                new Movie("Star Wars"),
                new Cinema("Cinema Wars", "Via dell donne", "Roma"),
                new Hall(3),
                "20:00",
                "2021-02-13"
        );
        projection.getHall().setSeatList(new ArrayList<>());
        for (int i = 0; i < 41; i++) {
            projection.getHall().getSeatList().add(new Seat((long) i % 4 + 4, true));

        }
        projection.getHall().getSeatList().get(7).setState(false);
        projectionList.add(projection);
        List<Seat> seatList = new ArrayList<>();
        for (int i = 0; i < 41; i++) {
            seatList.add(new Seat((long) i % 4 + 4, true));

        }
        hall1.setProjectionList(projectionList);
        hall1.setSeatList(seatList);
        hallList.add(hall1);
        cinema1.setHallList(hallList);
        return cinemaList;
    }

    @Override
    public List<Cinema> getCinema(Movie movie, String date) {
        List<Cinema> listCinema = getAllCinemaList();
        for(Cinema cinema: listCinema){ /*sono n*/
            for(Hall hall: cinema.getHallList()){ /*sono n*/
                for(Projection projection: hall.getProjectionList()){ /*sono n*/
                    if(projection.getMovie().getName().equals(movie.getName())&&projection.getDate().equals(date)){
                        listCinema.add(cinema);
                    }
                    /*ricerca all'ordine di n^3*/

                }
            }
        }
        return listCinema;
    }


}
