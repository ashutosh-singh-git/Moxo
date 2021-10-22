package com.moxo.app.entity.paw;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.Generated;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "paw_config")
public class PawConfigEntity {

    @Id
    @Generated
    private String id;
    private String type;
    private Object data;
    private String bn;
    private String os;
}
