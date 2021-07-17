package com.moxo.app.service;

import com.moxo.app.dto.PageableResponse;
import com.moxo.app.entity.FeedEntity;

public interface SearchService {

    public PageableResponse<FeedEntity> searchFeeds(Integer page, Integer size, String query);
}
