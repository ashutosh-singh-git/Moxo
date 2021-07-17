package com.moxo.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel
public class PostCommentsDto {

    @ApiModelProperty("Activity Id on which comment is made")
    private String aid;
    @ApiModelProperty("Comment Dto")
    private Comment comment;
}
