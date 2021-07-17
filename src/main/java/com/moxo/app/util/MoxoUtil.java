package com.moxo.app.util;

import com.moxo.app.dto.ActivityResponseDto;
import com.moxo.app.entity.ActivityEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;

import java.util.Set;

public class MoxoUtil {

    public static final Set<String> HTML_TYPES = Set.of(MediaType.TEXT_HTML_VALUE, "html");
    private static final int MAX_PAGE_SIZE = 20;

    public static Pageable pageableSortByScore(Integer page, Integer size) {

        int pg = page == null ? 0 : page;
        int sz = size == null || size > MAX_PAGE_SIZE ? MAX_PAGE_SIZE : size;

        return PageRequest.of(pg, sz, Sort.by("textScore"));
    }

    public static Pageable pageableSortByLatest(Integer page, Integer size) {

        int pg = page == null ? 0 : page;
        int sz = size == null || size > MAX_PAGE_SIZE ? MAX_PAGE_SIZE : size;

        return PageRequest.of(pg, sz, Sort.by("updatedAt"));
    }

    public static ActivityResponseDto convertActivityResponse(ActivityEntity entity) {
        return ActivityResponseDto.builder()
                .aid(entity.getId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .comments(entity.getComments())
                .description(entity.getDescription())
                .title(entity.getTitle())
                .dislikes(entity.getDislikes())
                .likes(entity.getLikes())
                .media(entity.getMedia())
                .user(entity.getUser())
                .build();
    }
}
