package com.ttbmp.cinehub.ui.web.viewpersonalschedule;

import com.ttbmp.cinehub.app.dto.ProjectionDto;

import java.util.List;

public class ProjectionListDto {

    private List<ProjectionDto> projections;

    public ProjectionListDto(List<ProjectionDto> projections) {
        this.projections = projections;
    }

    public List<ProjectionDto> getProjections() {
        return projections;
    }

    public void setProjections(List<ProjectionDto> projections) {
        this.projections = projections;
    }

}
