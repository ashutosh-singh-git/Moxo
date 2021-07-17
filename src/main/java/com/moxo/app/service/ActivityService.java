package com.moxo.app.service;

import com.moxo.app.dto.ActivityResponseDto;
import com.moxo.app.dto.BaseResponse;
import com.moxo.app.dto.Comment;
import com.moxo.app.dto.PageableResponse;
import com.moxo.app.dto.PostActivityDto;
import com.moxo.app.dto.PostCommentsDto;

public interface ActivityService {
    PageableResponse<ActivityResponseDto> fetchActivity(Integer page, Integer size);

    BaseResponse postActivity(PostActivityDto dto);

    PageableResponse<Comment> fetchComments(Integer page, Integer size, String aid);

    BaseResponse postComment(PostCommentsDto postCommentsDto);

}
