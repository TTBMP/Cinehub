package com.ttbmp.cinehub.app.dto;

public class ProjectionistDto extends EmployeeDto {

    public ProjectionistDto(String name, String surname, CinemaDto cinema) {
        super(name, surname, cinema);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        EmployeeDto other = (EmployeeDto) obj;
        return this.getName().equals(other.getName())
                && this.getSurname().equals(other.getSurname())
                && this.getCinema().equals(other.getCinema());
    }

    @Override
    public int hashCode() {
        /* ... */
        return 0;
    }
}
