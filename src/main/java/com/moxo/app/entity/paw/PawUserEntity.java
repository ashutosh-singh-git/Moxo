package com.moxo.app.entity.paw;

import com.moxo.app.dto.paw.PawUser;
import com.moxo.app.entity.BaseEntity;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "paw_user")
@Getter
@Setter
public class PawUserEntity extends BaseEntity {

    @Id
    @Generated
    private String id;
    private String name;
    private String msisdn;
    private String email;

    public PawUserEntity(PawUser pawUser) {
        this.name = pawUser.getName();
        this.msisdn = pawUser.getMsisdn();
        this.email = pawUser.getEmail();
    }
}
