package com.moxo.app.service.impl;

import com.moxo.app.config.MoxoCacheConfig;
import com.moxo.app.entity.FeedEntity;
import com.moxo.app.repository.FeedsRepository;
import com.moxo.app.service.SearchService;
import com.moxo.app.util.FeedUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = MoxoCacheConfig.SEARCH)
public class SearchServiceImpl implements SearchService {

    private final FeedsRepository feedsRepository;

    @Autowired
    public SearchServiceImpl(FeedsRepository feedsRepository) {
        this.feedsRepository = feedsRepository;
    }

    @Override
    @Cacheable
    public Slice<FeedEntity> searchFeeds(Integer page, Integer size, String query) {

        Pageable pageable = FeedUtil.pageableSortByScore(page, size);

        TextCriteria textCriteria = TextCriteria.forDefaultLanguage().matchingPhrase(query);
        return feedsRepository.findAllByStateTrue(textCriteria, pageable);
    }
}
