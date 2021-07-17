package com.moxo.app.controller;

import com.moxo.app.dto.PageableResponse;
import com.moxo.app.dto.PublisherDetails;
import com.moxo.app.entity.FeedEntity;
import com.moxo.app.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feed")
public class FeedController {

    @Autowired
    private FeedService feedService;

    @GetMapping
    public PageableResponse<FeedEntity> fetchData(@RequestParam Integer page, @RequestParam Integer size) {
        return feedService.getFeeds(page, size);
    }

    @PostMapping(value = "/schedule")
    public boolean scheduleData(@RequestBody PublisherDetails url) {
        return feedService.parseURL(url);
    }

    @PostMapping("/click")
    public boolean onClick(@RequestBody String id) {
        return feedService.onFeedClick(id);
    }
}
