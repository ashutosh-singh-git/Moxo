package com.moxo.app.controller;

import com.moxo.app.dto.ActivityResponseDto;
import com.moxo.app.dto.BaseResponse;
import com.moxo.app.dto.Comment;
import com.moxo.app.dto.PageableResponse;
import com.moxo.app.dto.PostActivityDto;
import com.moxo.app.dto.PostCommentsDto;
import com.moxo.app.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping
    public PageableResponse<ActivityResponseDto> fetchData(@RequestParam Integer page, @RequestParam Integer size) {
        return activityService.fetchActivity(page, size);
    }

    @PostMapping
    public BaseResponse postActivity(@RequestBody PostActivityDto dto) {
        return activityService.postActivity(dto);
    }

    @GetMapping(value = "/comments")
    public PageableResponse<Comment> fetchCommentsData(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String aid) {
        return activityService.fetchComments(page, size, aid);
    }

    @PostMapping(value = "/comments")
    public BaseResponse postCommentsData(@RequestBody PostCommentsDto postCommentsDto) {
        return activityService.postComment(postCommentsDto);
    }
}
