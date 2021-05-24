package com.ttbmp.cinehub.app.dto;

/**
 * @author Ivan Palmieri
 */
public class HallDto {

    private Integer id;
    private String name;

    public HallDto(Integer id) {
        this.id = id;
    }

    public HallDto(Integer id, String name) {
        this.id = id;
        this.name = name;
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
