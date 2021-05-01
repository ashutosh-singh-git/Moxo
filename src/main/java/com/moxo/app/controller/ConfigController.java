package com.moxo.app.controller;

import com.moxo.app.dto.ConfigDto;
import com.moxo.app.service.ConfigService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

    private final ConfigService configService;

    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }

    @PostMapping(value = "/addConfig")
    public String addConfig(@RequestBody ConfigDto dto) {
        configService.addConfig(dto);
        return "accepted";
    }

    @GetMapping(value = "/refreshConfig")
    public String addConfig() {
        configService.refreshConfig();
        return "accepted";
    }

}
