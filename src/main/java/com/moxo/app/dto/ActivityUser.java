package com.moxo.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class ActivityUser {

    @ApiModelProperty("User id of Activity Poster User")
    private String uid;
    @ApiModelProperty("Alias of user")
    private String alias;
    @ApiModelProperty("Alias of User")
    private String name;
    @ApiModelProperty("Small Thumbnail of User")
    private String thumbnail;
}
