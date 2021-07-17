package com.moxo.app.dto;

import com.moxo.app.entity.UserEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class UserInfoResponse {

    @ApiModelProperty("User Id")
    String uid;
    @ApiModelProperty(value = "User Name. Will be used for communication purpose", example = "Vikas Singh")
    String name;
    @ApiModelProperty(value = "User Alias. Visible by default", example = "linkass")
    String alias;
    @ApiModelProperty(value = "User Email. Either email of msisdn is required", example = "as@gmail.com")
    String email;
    @ApiModelProperty(value = "User Phone Number. Either email of msisdn is required", example = "+918223349290")
    String msisdn;

    public UserInfoResponse(UserEntity userEntity) {
        this.uid = userEntity.getUid();
        this.name = userEntity.getName();
        this.alias = userEntity.getAlias();
        this.email = userEntity.getEmail();
        this.msisdn = userEntity.getMsisdn();
    }
}
