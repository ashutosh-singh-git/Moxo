package com.moxo.app.service.impl;

import com.moxo.app.dto.PublisherDetails;
import com.moxo.app.entity.FeedEntity;
import com.moxo.app.repository.FeedsRepository;
import com.moxo.app.service.FeedParser;
import com.moxo.app.service.FeedService;
import com.moxo.app.util.FeedUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
public class FeedServiceImpl implements FeedService {

    private final static Logger LOGGER = LoggerFactory.getLogger(FeedServiceImpl.class);

    private final FeedsRepository feedsRepository;
    private final FeedParser feedParser;

    @Autowired
    public FeedServiceImpl(FeedsRepository feedsRepository, FeedParser feedParser) {
        this.feedsRepository = feedsRepository;
        this.feedParser = feedParser;
    }

    @Override
    public Slice<FeedEntity> getFeeds(Integer page, Integer size) {

        LOGGER.info("Feed request for: " + page + ", " + size);
        Pageable pageable = FeedUtil.pageableSortByScore(page, size);
        return feedsRepository.findAllByStateTrue(pageable);

    }

    @Override
    public boolean parseURL(PublisherDetails url) {
        feedParser.submit(url);
        return true;
    }
}
