package com.moxo.app.service.impl;

import com.moxo.app.config.MoxoCacheConfig;
import com.moxo.app.dto.PageableResponse;
import com.moxo.app.entity.FeedEntity;
import com.moxo.app.repository.FeedsRepository;
import com.moxo.app.service.SearchService;
import com.moxo.app.util.MoxoUtil;
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
    public PageableResponse<FeedEntity> searchFeeds(Integer page, Integer size, String query) {

        Pageable pageable = MoxoUtil.pageableSortByScore(page, size);
        TextCriteria textCriteria = TextCriteria.forDefaultLanguage().matchingPhrase(query);
        Slice<FeedEntity> slice = feedsRepository.findAllByStateTrue(textCriteria, pageable);

        return PageableResponse.<FeedEntity>builder()
                .content(slice.getContent())
                .empty(slice.isEmpty())
                .first(slice.isFirst())
                .last(slice.isLast())
                .number(slice.getNumber())
                .size(slice.getSize())
                .build();
    }
}
