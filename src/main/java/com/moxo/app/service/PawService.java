package com.moxo.app.service;

import com.moxo.app.dto.paw.FosterDetails;
import com.moxo.app.dto.paw.LoginUserResponse;
import com.moxo.app.dto.paw.PawConfig;
import com.moxo.app.dto.paw.PawPost;
import com.moxo.app.dto.paw.PawPostResponse;
import com.moxo.app.dto.paw.PawUser;
import com.moxo.app.dto.paw.UserProfile;
import com.moxo.app.entity.paw.PawPageEntity;

import java.util.List;

public interface PawService {

    PawPageEntity getLayout(String pageId, Integer page, Integer size);

    FosterDetails getFosterDetail(String fosterId);

    FosterDetails createFosterData(FosterDetails entity);

    PawPageEntity searchPage(String query);

    PawUser registerUser(PawUser dto);

    LoginUserResponse loginUser(PawUser dto);

    UserProfile fetchUserProfile(String profileId);

    PawConfig getClientConfig(String os, String bn);

    PawConfig saveConfig(String os, String bn, Object data);

    PawPostResponse createPost(PawPost data);

    PawPostResponse fetchPost(String id);

    List<PawPostResponse> fetchPaginatedPost(String pageId, String size);

    UserProfile updateProfile(UserProfile userProfile);
}
