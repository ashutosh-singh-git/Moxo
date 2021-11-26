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
    private Map<String, String> meta;

    @Data
    private static class PostUser {
        private String id;
        private String thumbnail;
        private String name;
        private String about;
    }
}
