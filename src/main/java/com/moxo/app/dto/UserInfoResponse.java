package com.moxo.app.dto;

import com.moxo.app.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoResponse {
    String uid;
    String name;
    String alias;
    String email;
    String msisdn;

    public UserInfoResponse(UserEntity userEntity) {
        this.uid = userEntity.getUid();
        this.name = userEntity.getName();
        this.alias = userEntity.getAlias();
        this.email = userEntity.getEmail();
        this.msisdn = userEntity.getMsisdn();
    }
}
