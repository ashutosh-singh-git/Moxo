package com.moxo.app.service;

import com.moxo.app.dto.ConfigDto;
import com.moxo.app.dto.UrlParserConfig;

public interface ConfigService {

    UrlParserConfig getUrlParserConfig();

    void addConfig(ConfigDto type);

    void refreshConfig();
}
