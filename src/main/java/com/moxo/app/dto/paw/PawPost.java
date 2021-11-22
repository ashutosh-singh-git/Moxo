package com.moxo.app.dto.paw;

import lombok.Data;

import java.util.Map;

@Data
public class PawPost {
    private String title;
    private String userId;
    private String desc;
    private String img;
    private Location location;
    private Map<String, String> meta;
}
