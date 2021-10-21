package com.moxo.app.dto.paw;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum MediaType {
    @JsonProperty("videos")
    VIDEO,
    @JsonProperty("images")
    IMAGE;
}
