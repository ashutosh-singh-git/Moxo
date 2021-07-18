package com.moxo.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@ApiModel
public class PostActivityDto {

    @ApiModelProperty(value = "Title of activity")
    private String title;
    @ApiModelProperty(value = "Description of activity. Max of 500 words")
    private String description;
    @ApiModelProperty(value = "Uploaded media metadata")
    private ActivityMedia media;
    @ApiModelProperty(value = "Activity posting user meta")
    private ActivityUser user;
}
