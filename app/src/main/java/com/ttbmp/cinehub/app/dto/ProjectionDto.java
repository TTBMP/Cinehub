package com.ttbmp.cinehub.app.dto;

import com.ttbmp.cinehub.domain.Projection;
import lombok.Value;

/**
 * @author Ivan Palmieri
 */
@Value
public class ProjectionDto {

    int id;
    String date;
    String startTime;
    long basePrice;
    MovieDto movieDto;
    HallDto hallDto;

    public ProjectionDto(Projection projection) {
        this.id = projection.getId();
        this.date = projection.getDate();
        this.startTime = projection.getStartTime();
        this.basePrice = projection.getBasePrice();
        this.movieDto = new MovieDto(projection.getMovie());
        this.hallDto = new HallDto(projection.getHall());
    }

}
