package com.moxo.app.service;

import com.moxo.app.entity.FeedEntity;
import org.springframework.data.domain.Slice;

public interface SearchService {

    public Slice<FeedEntity> searchFeeds(Integer page, Integer size, String query);
}
