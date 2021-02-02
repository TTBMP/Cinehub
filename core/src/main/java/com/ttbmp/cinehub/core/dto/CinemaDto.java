package com.ttbmp.cinehub.core.dto;

/**
 * @author Massimo Mazzetti
 */

public class CinemaDto {

    private String name;

    public CinemaDto() {
    }

    public CinemaDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        CinemaDto other = (CinemaDto) obj;
        return this.name.equals(other.name);
    }
    @Override
    public int hashCode() {
        /* ... */
        return 0;
    }
}