package com.ttbmp.cinehub.app.usecase.viewpersonalschedule.reply;

import com.ttbmp.cinehub.app.dto.ProjectionDto;
import lombok.Value;

import java.util.List;

/**
 * @author Fabio Buracchi
 */
@Value
public class ProjectionListReply {

    List<ProjectionDto> projectionDtoList;

}
