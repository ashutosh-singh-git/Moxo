package com.moxo.app.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.moxo.app.dto.ConfigType;
import com.moxo.app.dto.UrlParserConfig;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = UrlParserConfig.class, name = "URL_PARSER_CONFIG")
})
public interface BaseConfig {
    ConfigType getType();
}
