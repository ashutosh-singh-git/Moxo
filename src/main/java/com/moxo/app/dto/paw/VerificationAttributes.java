package com.moxo.app.dto.paw;

import lombok.Data;

@Data
public class VerificationAttributes {
    private Boolean email;
    private Boolean msisdn;
    private Boolean license;
}
