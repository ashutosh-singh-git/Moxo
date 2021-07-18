package com.moxo.app.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ActivityMedia {

    @ApiModelProperty("Type of media")
    private ActivityType type;
    @ApiModelProperty("Url of uploaded media")
    private String link;
    @ApiModelProperty("Description of uploaded meta")
    private String caption;
}
