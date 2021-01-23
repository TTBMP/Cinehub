package com.ttbmp.cinehub.core.repository;

import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.entity.Hall;
import com.ttbmp.cinehub.core.utilities.result.Result;

import java.util.List;

public interface HallRepository {
    List<Hall> getCinemaHallList(Cinema cinema);
}
