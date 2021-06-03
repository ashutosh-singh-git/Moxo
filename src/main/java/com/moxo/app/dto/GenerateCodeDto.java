package com.moxo.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenerateCodeDto {

    private String email;
    private String msisdn;
    private LoginMode logMode;
}
