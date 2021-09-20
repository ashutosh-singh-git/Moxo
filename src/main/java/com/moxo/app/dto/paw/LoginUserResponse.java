package com.moxo.app.dto.paw;

import com.moxo.app.entity.paw.PawUserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserResponse {

    private String id;
    private String name;
    private String email;
    private String msisdn;
    private String token;

    public LoginUserResponse(PawUserEntity pawUser, String token) {
        this.id = pawUser.getId();
        this.name = pawUser.getName();
        this.email = pawUser.getEmail();
        this.msisdn = pawUser.getMsisdn();
        this.token = token;
    }
}
