package com.ttbmp.cinehub.app.dto;

import com.ttbmp.cinehub.domain.Hall;
import lombok.Value;

/**
 * @author Ivan Palmieri
 */
@Value
public class HallDto {

    Integer id;
    String name;

    public HallDto(Hall hall) {
        this.id = hall.getId();
        this.name = hall.getName();
    }

}
