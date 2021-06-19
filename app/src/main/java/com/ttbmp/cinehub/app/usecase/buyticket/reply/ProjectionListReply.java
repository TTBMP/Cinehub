package com.ttbmp.cinehub.app.usecase.buyticket.reply;

import com.ttbmp.cinehub.app.dto.ProjectionDto;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class ProjectionListReply {

    List<ProjectionDto> projectionDtoList;
    List<String> projectionDtoTime = new ArrayList<>();

    public ProjectionListReply(List<ProjectionDto> projectionDtoList) {
        this.projectionDtoList = projectionDtoList;
        for (var projection : projectionDtoList) {
            addProjectionDtoTime(projection.getStartTime());
        }

    }

    public void addProjectionDtoTime(String projectionTime) {
        this.projectionDtoTime.add(projectionTime);
    }

}
