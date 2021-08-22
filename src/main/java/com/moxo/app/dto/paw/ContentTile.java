package com.moxo.app.dto.paw;

import lombok.Getter;

import java.util.Map;
import java.util.Set;

@Getter
public class ContentTile {
    private String id;
    private String title;
    private String link;
    private String description;
    private String rating;
    private Set<String> tags;
    private LinkType linkType;
    private Map<ImageType, String> img;
}
