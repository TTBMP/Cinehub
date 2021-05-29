package com.ttbmp.cinehub.app.dto;

import com.ttbmp.cinehub.domain.Hall;

/**
 * @author Ivan Palmieri
 */
public class HallDto {

    private Integer id;
    private String name;

    public HallDto(Hall hall) {
        this.id = hall.getId();
        this.name = hall.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
