package com.ttbmp.cinehub.app.usecase.viewpersonalschedule.reply;

import com.ttbmp.cinehub.app.dto.ProjectionDto;

import java.util.List;

/**
 * @author Fabio Buracchi
 */
public class ProjectionListReply {

    private List<ProjectionDto> projectionDtoList;

    public ProjectionListReply(List<ProjectionDto> projectionDtoList) {
        this.projectionDtoList = projectionDtoList;
    }

    public List<ProjectionDto> getProjectionDtoList() {
        return projectionDtoList;
    }

    public void setProjectionDtoList(List<ProjectionDto> projectionDtoList) {
        this.projectionDtoList = projectionDtoList;
    }

}
