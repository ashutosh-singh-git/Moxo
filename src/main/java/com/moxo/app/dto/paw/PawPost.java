package com.moxo.app.dto.paw;

import lombok.Data;

import java.util.Map;

@Data
public class PawPost {
    private String title;
    private PostUser user;
    private String desc;
    private String img;
    private Location location;
    private PostType type;
    private Map<String, String> meta;

}
