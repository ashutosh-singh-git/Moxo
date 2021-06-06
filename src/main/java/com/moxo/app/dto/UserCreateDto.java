package com.moxo.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateDto {

    private String name;
    private String email;
    private String msisdn;
    private String passwd;
    private LoginMode logMode;
    private AcquisitionMode acqMode;
    private String otp;
}
