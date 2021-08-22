package com.moxo.app.service.impl;

import com.moxo.app.entity.PawPageEntity;
import com.moxo.app.repository.PawPageRepository;
import com.moxo.app.service.PawService;
import com.moxo.app.util.MoxoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.moxo.app.util.ResponseCode.P001;

@Service
@Slf4j
public class PawServiceImpl implements PawService {

    @Autowired
    private PawPageRepository pageRepository;

    @Override
    public PawPageEntity getLayout(String pageId, Integer page, Integer size) {

        try {
            Optional<PawPageEntity> entity = pageRepository.findById(pageId);

            if(entity.isPresent()) {
                return entity.get();
            }
        } catch (Exception e) {
            log.error("error while fetching page : ", e);
            throw new MoxoException(P001, e.getMessage(), e);
        }
        throw new MoxoException(P001);
    }

}
