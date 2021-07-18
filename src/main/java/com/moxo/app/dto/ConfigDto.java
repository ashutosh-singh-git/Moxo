package com.moxo.app.dto;

import com.moxo.app.entity.BaseConfig;

public record ConfigDto(BaseConfig config, boolean state) {
}
