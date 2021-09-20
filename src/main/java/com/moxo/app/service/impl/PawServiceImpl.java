package com.moxo.app.service.impl;

import com.moxo.app.dto.BaseResponse;
import com.moxo.app.dto.paw.FosterDetails;
import com.moxo.app.dto.paw.LoginUserResponse;
import com.moxo.app.dto.paw.PawUser;
import com.moxo.app.entity.paw.FosterDetailEntity;
import com.moxo.app.entity.paw.PawPageEntity;
import com.moxo.app.entity.paw.PawUserEntity;
import com.moxo.app.repository.paw.FosterRepository;
import com.moxo.app.repository.paw.PawPageRepository;
import com.moxo.app.repository.paw.PawUserRepository;
import com.moxo.app.service.PawService;
import com.moxo.app.util.MoxoException;
import com.moxo.app.util.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.moxo.app.util.ResponseCode.P001;
import static com.moxo.app.util.ResponseCode.P002;

@Service
@Slf4j
public class PawServiceImpl implements PawService {

    @Autowired
    private PawPageRepository pageRepository;
    @Autowired
    private FosterRepository fosterRepository;
    @Autowired
    private PawUserRepository userRepository;

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

    public FosterDetails getFosterDetail(String fosterId) {

        try {
            Optional<FosterDetailEntity> entity = fosterRepository.findById(fosterId);

            if(entity.isPresent()) {
                return new FosterDetails(entity.get());
            }
        } catch (Exception e) {
            log.error("error while foster details : ", e);
            throw new MoxoException(P002, e.getMessage(), e);
        }
        throw new MoxoException(P002);
    }

    public FosterDetails createFosterData(FosterDetails dto) {
        try {
            FosterDetailEntity detailEntity = fosterRepository.save(new FosterDetailEntity(dto));

            return new FosterDetails(detailEntity);
        } catch (Exception e) {
            log.error("error while creating foster : ", e);
            throw new MoxoException(ResponseCode.P003, e.getMessage(), e);
        }
    }

    @Override
    public PawPageEntity searchPage(String query) {
        return null;
    }

    @Override
    public PawUser registerUser(PawUser dto) {
        try {
            PawUserEntity pawUser = userRepository.save(new PawUserEntity(dto));
            return new PawUser(pawUser);
        } catch (Exception e) {
            log.error("error while creating foster : ", e);
            throw new MoxoException(ResponseCode.P004, e.getMessage(), e);
        }
    }

    @Override
    public LoginUserResponse loginUser(PawUser dto) {
        try {
            Optional<PawUserEntity> pawUser = userRepository.findByEmail(dto.getEmail());
            if(pawUser.isPresent()) {
                return new LoginUserResponse(pawUser.get)
            }
            throw new MoxoException(ResponseCode.P004, "User does not exist");
        } catch (Exception e) {
            log.error("error while creating foster : ", e);
            throw new MoxoException(ResponseCode.P004, e.getMessage(), e);
        }
    }
}
