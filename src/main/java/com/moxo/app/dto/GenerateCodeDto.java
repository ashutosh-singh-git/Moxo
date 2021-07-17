package com.moxo.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class GenerateCodeDto {

    @ApiModelProperty("Valid Email Id. Otp will be sent to this mail")
    private String email;
    @ApiModelProperty("Valid Phone number. Otp will be sent to this number")
    private String msisdn;
    @ApiModelProperty("Login mode for login decisions")
    private LoginMode logMode;
}
