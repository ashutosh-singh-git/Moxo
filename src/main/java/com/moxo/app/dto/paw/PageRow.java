package com.moxo.app.dto.paw;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
public class PageRow {

    @Id
    private String id;
    private String title;
    private LayoutType layoutType;
    private TileType tileType;
    private List<ContentTile> tiles;
}
