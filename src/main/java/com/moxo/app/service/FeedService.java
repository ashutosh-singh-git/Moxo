package com.moxo.app.service;

import com.moxo.app.dto.PublisherDetails;
import com.moxo.app.entity.FeedEntity;
import org.springframework.data.domain.Slice;

public interface FeedService {

    Slice<FeedEntity> getFeeds(Integer page, Integer size);

    boolean parseURL(PublisherDetails url);

    boolean onFeedClick(String feedId);
}
