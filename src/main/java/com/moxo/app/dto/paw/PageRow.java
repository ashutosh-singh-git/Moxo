package com.moxo.app.dto.paw;

import lombok.Getter;

import java.util.List;

@Getter
public class PageRow {
    private String id;
    private String title;
    private LayoutType layoutType;
    private TileType tileType;
    private List<ContentTile> tiles;
}
