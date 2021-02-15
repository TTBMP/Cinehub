package com.ttbmp.cinehub.app.dto;

/**
 * @author Fabio Buracchi
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UsherDto extends EmployeeDto {

    public UsherDto(String id, String name, String surname, CinemaDto cinema) {
        super(id, name, surname, cinema);
    }

    @Override
    public String toString() {
        return "usher";
    }

}
