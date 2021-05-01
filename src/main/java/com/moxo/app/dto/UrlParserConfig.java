package com.moxo.app.dto;

import com.moxo.app.entity.BaseConfig;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UrlParserConfig implements BaseConfig {

    private ConfigType type = ConfigType.URL_PARSER_CONFIG;
    private List<PublisherDetails> publisherList;

}

