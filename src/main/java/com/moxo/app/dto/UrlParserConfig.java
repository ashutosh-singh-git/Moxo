package com.moxo.app.dto;

import com.moxo.app.entity.BaseConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

@Getter
@Setter
public class UrlParserConfig implements BaseConfig {

    private ConfigType type = ConfigType.URL_PARSER_CONFIG;
    private List<PublisherDetails> publisherList;

    @Getter
    @Setter
    public static class PublisherDetails {
        private String url;
        private String publisher;
    }
}

