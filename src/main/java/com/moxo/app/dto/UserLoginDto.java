package com.moxo.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class UserLoginDto {
    @ApiModelProperty("Valid msisdn. Used while creating user")
    private String msisdn;
    @ApiModelProperty("Valid email id. Used while creating user")
    private String email;
    @ApiModelProperty("Password while creating user")
    private String passwd;
    @ApiModelProperty("Mode to login. depends on whether user has created an entry")
    private LoginMode logMode;
}
