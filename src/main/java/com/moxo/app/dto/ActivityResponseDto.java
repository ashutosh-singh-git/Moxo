package com.moxo.app.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ActivityResponseDto {

    private final String aid;
    private final String title;
    private final String description;
    private final ActivityMedia media;
    private final ActivityUser user;
    private final int likes;
    private final int dislikes;
    private final List<Comment> comments;
    private final Long createdAt;
    private final Long updatedAt;
}
