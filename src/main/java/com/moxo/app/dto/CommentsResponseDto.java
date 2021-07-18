package com.moxo.app.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CommentsResponseDto {

    private String aid;
    private List<Comment> comments;
}
