package com.moxo.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@ApiModel
public class BaseResponse {
    @ApiModelProperty("Response message")
    private String msg;
    @ApiModelProperty("Error Code in case of status is false")
    private String errorCode;
    @ApiModelProperty("True if request is success")
    private boolean success;
}
