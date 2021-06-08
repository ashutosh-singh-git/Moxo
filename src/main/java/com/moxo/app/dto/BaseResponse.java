package com.moxo.app.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BaseResponse {
    private String msg;
    private String code;
    private boolean status;
}
