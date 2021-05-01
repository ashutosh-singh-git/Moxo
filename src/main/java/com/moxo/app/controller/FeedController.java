package com.moxo.app.controller;

import com.moxo.app.dto.PublisherDetails;
import com.moxo.app.entity.FeedEntity;
import com.moxo.app.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedController {

    @Autowired
    private FeedService feedService;

    @GetMapping(value = "/feed")
    public Slice<FeedEntity> fetchData(@RequestParam Integer page, @RequestParam Integer size) {
        return feedService.getFeeds(page, size);
    }

    @PostMapping(value = "/schedule")
    public boolean scheduleData(@RequestBody PublisherDetails url) {
        return feedService.parseURL(url);
    }
}
