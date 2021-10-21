package com.moxo.app.dto.paw;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class ContentTile {

    @Id
    private String id;
    private String title;
    private String link;
    private String description;
    private String rating;
    private Set<String> tags;
    private LinkType linkType;
    private Map<ImageType, String> img;
}
