package com.moxo.app.service;

import com.moxo.app.dto.BaseResponse;
import com.moxo.app.dto.paw.FosterDetails;
import com.moxo.app.dto.paw.PawUser;
import com.moxo.app.entity.paw.PawPageEntity;

public interface PawService {

    PawPageEntity getLayout(String pageId, Integer page, Integer size);

    FosterDetails getFosterDetail(String fosterId);

    FosterDetails createFosterData(FosterDetails entity);

    PawPageEntity searchPage(String query);

    PawUser registerUser(PawUser dto);

    LoginUserResponse loginUser(PawUser dto);
}
