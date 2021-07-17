package com.moxo.app.service.impl;

import com.moxo.app.dto.ActivityResponseDto;
import com.moxo.app.dto.BaseResponse;
import com.moxo.app.dto.Comment;
import com.moxo.app.dto.PageableResponse;
import com.moxo.app.dto.PostActivityDto;
import com.moxo.app.dto.PostCommentsDto;
import com.moxo.app.dto.State;
import com.moxo.app.entity.ActivityEntity;
import com.moxo.app.repository.ActivityRepository;
import com.moxo.app.service.ActivityService;
import com.moxo.app.util.MoxoException;
import com.moxo.app.util.MoxoUtil;
import com.moxo.app.util.ResponseCode;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public PageableResponse<ActivityResponseDto> fetchActivity(Integer page, Integer size) {
        Pageable pageable = MoxoUtil.pageableSortByLatest(page, size);
        Slice<ActivityEntity> slice = activityRepository.findAllByStateEquals(State.ACTIVE, pageable);

        List<ActivityResponseDto> contents = slice.getContent().stream().map(MoxoUtil::convertActivityResponse).collect(Collectors.toList());
        return PageableResponse.<ActivityResponseDto>builder()
                .content(contents)
                .empty(slice.isEmpty())
                .first(slice.isFirst())
                .last(slice.isLast())
                .number(slice.getNumber())
                .size(slice.getSize())
                .build();
    }


    @Override
    public BaseResponse postActivity(PostActivityDto dto) {

        try {
            ActivityEntity activityEntity = new ActivityEntity();
            activityEntity.setTitle(dto.getTitle());
            activityEntity.setDescription(dto.getDescription());
            activityEntity.setMedia(dto.getMedia());
            activityEntity.setUser(dto.getUser());
            activityRepository.save(activityEntity);

            return BaseResponse.builder()
                    .success(true)
                    .msg("Activity Posted")
                    .build();

        } catch (Exception e) {
            throw new MoxoException(ResponseCode.M002, e.getMessage(), e);
        }
    }

    @Override
    public PageableResponse<Comment> fetchComments(Integer page, Integer size, String aid) {
        Optional<ActivityEntity> entityOptional = activityRepository.findById(aid);
        if(entityOptional.isPresent()) {
            List<Comment> comments = entityOptional.get().getComments();
            PageableResponse.PageableResponseBuilder<Comment> builder = PageableResponse.builder();
            builder.size(comments.size());
            builder.first(page == 0);
            builder.empty(comments.isEmpty());
            builder.content(new ArrayList<>());
            if (!comments.isEmpty()) {
                int offset = page * size;
                int count = (page + 1) * size;
                builder.last(comments.size() <= offset + size);
                if (offset + count > comments.size()) {
                    builder.content(comments.subList(offset < comments.size() ? offset : Math.max(comments.size() - size, 0), comments.size() - 1));
                } else {
                    builder.content(comments.subList(offset, count));
                }
            }
            return builder.build();
        }

        throw new MoxoException(ResponseCode.M003);
    }

    @Override
    public BaseResponse postComment(PostCommentsDto postCommentsDto) {
        Optional<ActivityEntity> entityOptional = activityRepository.findById(postCommentsDto.getAid());

        if(entityOptional.isPresent()){
            List<Comment> comments = entityOptional.get().getComments();
            Comment comment = postCommentsDto.getComment();
            comment.setId(UUID.randomUUID().toString());
            comments.add(comment);
            activityRepository.save(entityOptional.get());
        }

        throw new MoxoException(ResponseCode.M003);
    }
}
