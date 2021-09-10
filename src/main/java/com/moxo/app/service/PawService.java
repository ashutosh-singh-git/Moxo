package com.moxo.app.service;

import com.moxo.app.dto.paw.FosterDetails;
import com.moxo.app.entity.PawPageEntity;

public interface PawService {

    PawPageEntity getLayout(String pageId, Integer page, Integer size);

    FosterDetails getFosterDetail(String fosterId);

    FosterDetails createFosterData(FosterDetails entity);
}
