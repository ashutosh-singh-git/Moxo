package com.moxo.app.service;

import com.moxo.app.entity.FeedEntity;
import org.springframework.data.domain.Slice;

public interface FeedService {


    public Slice<FeedEntity> getFeeds(Integer page, Integer size);

}
