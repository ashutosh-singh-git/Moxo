package com.moxo.app.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class FeedUtil {

    private static final int MAX_PAGE_SIZE = 20;

    public static Pageable pageableSortByScore(Integer page, Integer size) {

        int pg = page == null ? 0 : page;
        int sz = size == null || size > MAX_PAGE_SIZE ? MAX_PAGE_SIZE : size;

        return PageRequest.of(pg, sz, Sort.by("textScore"));
    }
}
