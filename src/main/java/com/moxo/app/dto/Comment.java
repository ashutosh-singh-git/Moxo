package com.moxo.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class Comment {

    @ApiModelProperty(value = "Unique id of comment per activity", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    private String id;
    @ApiModelProperty("Comments. Max 500 words")
    private String comment;
    @ApiModelProperty("User id who is commenting")
    private String uid;
}
