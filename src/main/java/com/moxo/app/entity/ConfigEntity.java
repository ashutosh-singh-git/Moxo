package com.moxo.app.entity;

import com.moxo.app.dto.ConfigType;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "config")
@Getter
@Setter
public class ConfigEntity {

    @Id
    @Generated
    private String id;

    private BaseConfig config;

    @Version
    private Long version;

    @CreatedDate
    private Long createdAt;

    @LastModifiedDate
    private Long updatedAt;

    private boolean state;

}
