package com.moxo.app.service;

import com.moxo.app.dto.PageableResponse;
import com.moxo.app.dto.PublisherDetails;
import com.moxo.app.entity.FeedEntity;

public interface FeedService {

    PageableResponse<FeedEntity> getFeeds(Integer page, Integer size);

    boolean parseURL(PublisherDetails url);

    boolean onFeedClick(String feedId);
}
