package com.ttbmp.cinehub.app.repository.cinema;

import com.ttbmp.cinehub.app.repository.hall.HallRepository;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.Hall;

import java.util.List;

/**
 * @author Fabio Buracchi
 */
public class CinemaProxy extends Cinema {

    private final HallRepository hallRepository;

    private boolean isHallListLoaded = false;

    public CinemaProxy(int id, String name, String city, String address, HallRepository hallRepository) {
        super(id, name, city, address, null);
        this.hallRepository = hallRepository;
    }

    @Override
    public List<Hall> getHallList() {
        if (!isHallListLoaded) {
            setHallList(hallRepository.getHallList(this.getId()));
        }
        return super.getHallList();
    }

    @Override
    public void setHallList(List<Hall> hallList) {
        isHallListLoaded = true;
        super.setHallList(hallList);
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
