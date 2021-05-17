package com.moxo.app.service.impl;

import com.moxo.app.dto.ConfigDto;
import com.moxo.app.dto.ConfigType;
import com.moxo.app.dto.UrlParserConfig;
import com.moxo.app.entity.BaseConfig;
import com.moxo.app.entity.ConfigEntity;
import com.moxo.app.repository.ConfigsRepository;
import com.moxo.app.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.List;

@Service
public class ConfigServiceImpl implements ConfigService {

    private final ConfigsRepository repository;
    private final EnumMap<ConfigType, BaseConfig> enumMap = new EnumMap<>(ConfigType.class);

    @Autowired
    public ConfigServiceImpl(ConfigsRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    void init() {
        List<ConfigEntity> allByStateTrue = repository.findAllByStateTrue();
        for (ConfigEntity configEntity : allByStateTrue) {
            if(configEntity.getConfig() != null) {
                enumMap.put(configEntity.getConfig().getType(), configEntity.getConfig());
            }
        }
    }

    public UrlParserConfig getUrlParserConfig() {
        return (UrlParserConfig) enumMap.get(ConfigType.URL_PARSER_CONFIG);
    }

    @Override
    public void addConfig(ConfigDto configDto) {
        ConfigEntity entity = new ConfigEntity();
        entity.setConfig(configDto.config());
        entity.setState(configDto.state());

        repository.save(entity);
    }

    @Override
    public void refreshConfig() {
        init();
    }

}
