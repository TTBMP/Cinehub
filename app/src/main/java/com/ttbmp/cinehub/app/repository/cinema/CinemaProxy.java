package com.ttbmp.cinehub.app.repository.cinema;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.LazyLoadingException;
import com.ttbmp.cinehub.app.repository.RepositoryException;
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

    public CinemaProxy(ServiceLocator serviceLocator, int id, String name, String city, String address) {
        super(id, name, city, address, null);
        this.hallRepository = serviceLocator.getService(HallRepository.class);
    }

    @Override
    public List<Hall> getHallList() {
        if (!isHallListLoaded) {
            try {
                setHallList(hallRepository.getHallList(this));
            } catch (RepositoryException e) {
                throw new LazyLoadingException(e.getMessage());
            }
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
