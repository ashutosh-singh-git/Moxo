package com.moxo.app.entity;

import com.moxo.app.dto.AcquisitionMode;
import com.moxo.app.dto.Gender;
import com.moxo.app.dto.LoginMode;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collation = "user")
@Getter
@Setter
@Builder
@NoArgsConstructor
public class UserEntity extends BaseEntity {

    @Id
    @Generated
    private String uid;
    private String name;
    private String alias;
    private String portraitImgUrl;
    private String thumbnailImgUrl;
    private String email;
    private String msisdn;
    private int age;
    private Gender gender;
    private Set<LoginMode> logMode;
    private String passwd;
    private AcquisitionMode acqMode;
    private Long lastLoginAt;
}
