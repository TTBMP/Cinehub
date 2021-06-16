package com.ttbmp.cinehub.app.usecase.buyticket.reply;

import com.ttbmp.cinehub.app.dto.ProjectionDto;

import java.util.ArrayList;
import java.util.List;

public class ProjectionListReply {


    private final List<ProjectionDto> projectionDtoList;
    private final List<String> projectionDtoTime = new ArrayList<>();

    public ProjectionListReply(List<ProjectionDto> projectionDtoList) {
        this.projectionDtoList = projectionDtoList;
        for (var projection : projectionDtoList) {
            addProjectionDtoTime(projection.getStartTime());
        }

    }


    public List<ProjectionDto> getProjectionDtoList() {
        return projectionDtoList;

    }

    public List<String> getProjectionDtoTime() {
        return projectionDtoTime;
    }


    public void addProjectionDtoTime(String projectionTime) {
        this.projectionDtoTime.add(projectionTime);
    }
}