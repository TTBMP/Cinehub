package com.ttbmp.cinehub.app.dto;

public class UsherDto extends EmployeeDto {

    public UsherDto(int id, String name, String surname, CinemaDto cinema) {
        super(id, name, surname, cinema);
    }

    @Override
    public String toString() {
        return "usher";
    }

}
