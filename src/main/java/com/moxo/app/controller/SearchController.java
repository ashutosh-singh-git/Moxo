package com.moxo.app.controller;

import com.moxo.app.dto.PageableResponse;
import com.moxo.app.entity.FeedEntity;
import com.moxo.app.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping(value = "/search")
    public PageableResponse<FeedEntity> fetchData(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String query) {
        return searchService.searchFeeds(page, size, query);
    }
}
