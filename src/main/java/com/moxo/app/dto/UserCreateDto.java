package com.moxo.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class UserCreateDto {

    @ApiModelProperty("Full Name of User. Will be used for communication purpose")
    private String name;
    @ApiModelProperty("Email of User. Required based on login mode")
    private String email;
    @ApiModelProperty(value = "Msisdn of User. Required based on login mode", example = "+9189756433903")
    private String msisdn;
    @ApiModelProperty("Password. Min 6 characters, Max 10 characters")
    private String passwd;
    @ApiModelProperty("Login Mode")
    private LoginMode logMode;
    @ApiModelProperty("Need to be calculated by client")
    private AcquisitionMode acqMode;
    @ApiModelProperty("Otp sent on mail or msisdn. Use generate Otp api to get this")
    private String otp;
}
