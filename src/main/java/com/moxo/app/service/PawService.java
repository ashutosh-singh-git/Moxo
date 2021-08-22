package com.moxo.app.service;

import com.moxo.app.entity.PawPageEntity;

public interface PawService {

    public PawPageEntity getLayout(String pageId, Integer page, Integer size);
}
