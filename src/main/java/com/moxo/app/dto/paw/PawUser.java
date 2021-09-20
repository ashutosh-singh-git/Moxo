package com.moxo.app.dto.paw;

import com.moxo.app.entity.paw.PawUserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PawUser {

    private String id;
    private String name;
    private String email;
    private String msisdn;
    private String otp;

    public PawUser(PawUserEntity pawUser) {
        id = pawUser.getId();
        name = pawUser.getName();
        email = pawUser.getEmail();
        msisdn = pawUser.getMsisdn();
    }
}
