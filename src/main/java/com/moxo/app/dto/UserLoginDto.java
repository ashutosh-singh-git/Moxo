package com.moxo.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDto {
    private String msisdn;
    private String email;
    private String passwd;
    private LoginMode logMode;
}
