package com.moxo.app.service.impl;

import com.moxo.app.dto.paw.FosterDetails;
import com.moxo.app.dto.paw.LoginUserResponse;
import com.moxo.app.dto.paw.PawConfig;
import com.moxo.app.dto.paw.PawPost;
import com.moxo.app.dto.paw.PawPostResponse;
import com.moxo.app.dto.paw.PawUser;
import com.moxo.app.dto.paw.UserProfile;
import com.moxo.app.entity.paw.FosterDetailEntity;
import com.moxo.app.entity.paw.PawConfigEntity;
import com.moxo.app.entity.paw.PawPageEntity;
import com.moxo.app.entity.paw.PawPostEntity;
import com.moxo.app.entity.paw.PawUserEntity;
import com.moxo.app.repository.paw.FosterRepository;
import com.moxo.app.repository.paw.PawConfigRepository;
import com.moxo.app.repository.paw.PawPageRepository;
import com.moxo.app.repository.paw.PawPostRepository;
import com.moxo.app.repository.paw.PawUserRepository;
import com.moxo.app.service.PawService;
import com.moxo.app.util.MoxoException;
import com.moxo.app.util.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Autowired
    private PawConfigRepository configRepository;
    @Autowired
    private PawPostRepository pawPostRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PawPageEntity getLayout(String pageId, Integer page, Integer size) {

        try {
            Optional<PawPageEntity> entity = pageRepository.findById(pageId);

            if (entity.isPresent()) {
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

            if (entity.isPresent()) {
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
            if (pawUser.isPresent()) {
                return new LoginUserResponse(pawUser.get(), "");
            }
            throw new MoxoException(ResponseCode.P005, "User does not exist");
        } catch (Exception e) {
            log.error("error while logging user : ", e);
            throw new MoxoException(ResponseCode.P005, e.getMessage(), e);
        }
    }

    @Override
    public UserProfile fetchUserProfile(String profileId) {
        try {
            Optional<PawUserEntity> pawUser = userRepository.findById(profileId);
            if (pawUser.isPresent()) {
                return modelMapper.map(pawUser.get(), UserProfile.class);
            }
            throw new MoxoException(ResponseCode.P005, "User does not exist");
        } catch (Exception e) {
            log.error("error while logging user : ", e);
            throw new MoxoException(ResponseCode.P005, e.getMessage(), e);
        }
    }

    @Override
    public UserProfile updateProfile(UserProfile userProfile) {
        try {
            Optional<PawUserEntity> pawUser = userRepository.findById(userProfile.getId());
            if (!pawUser.isPresent()) {
                throw new MoxoException(ResponseCode.P005, "User does not exist");
            }
            PawUserEntity pawUserEntity = modelMapper.map(userProfile, PawUserEntity.class);
            PawUserEntity saved = userRepository.save(pawUserEntity);
            return modelMapper.map(saved, UserProfile.class);
        } catch (Exception e) {
            log.error("error while updating user profile : ", e);
            throw new MoxoException(ResponseCode.P005, e.getMessage(), e);
        }
    }

    @Override
    public PawConfig getClientConfig(String os, String bn) {
        try {
            Optional<PawConfigEntity> entityOptional = configRepository.findByOsAndBn(os, bn);
            if (entityOptional.isPresent()) {
                return modelMapper.map(entityOptional.get(), PawConfig.class);
            }
            throw new MoxoException(ResponseCode.P006, "Config Not present");
        } catch (Exception e) {
            log.error("error while getting config : ", e);
            throw new MoxoException(ResponseCode.P006, e.getMessage(), e);
        }
    }

    @Override
    public PawConfig saveConfig(String os, String bn, Object data) {
        try {
            Optional<PawConfigEntity> entityOptional = configRepository.findByOsAndBn(os, bn);
            if (entityOptional.isPresent()) {
                PawConfigEntity pawConfigEntity = entityOptional.get();
                pawConfigEntity.setData(data);
                PawConfigEntity saved = configRepository.save(pawConfigEntity);
                return modelMapper.map(saved, PawConfig.class);
            }

            // Creating new config
            PawConfigEntity newObj = new PawConfigEntity();
            newObj.setBn(bn);
            newObj.setOs(os);
            newObj.setData(data);
            newObj.setType("appConfig");
            PawConfigEntity saved = configRepository.save(newObj);
            return modelMapper.map(saved, PawConfig.class);
        } catch (Exception e) {
            log.error("error while getting config : ", e);
            throw new MoxoException(ResponseCode.P006, e.getMessage(), e);
        }
    }

    @Override
    public PawPostResponse createPost(PawPost data) {
        try {
            PawPostEntity map = modelMapper.map(data, PawPostEntity.class);
            PawPostEntity savedEntity = pawPostRepository.save(map);
            return modelMapper.map(savedEntity, PawPostResponse.class);
        } catch (Exception e) {
            log.error("error while creating post : " + data, e);
            throw new MoxoException(ResponseCode.P007, e.getMessage(), e);
        }
    }

    @Override
    public PawPostResponse fetchPost(String id) {
        try {
            Optional<PawPostEntity> entityOptional = pawPostRepository.findById(id);
            if (entityOptional.isPresent()) {
                return modelMapper.map(entityOptional.get(), PawPostResponse.class);
            }
            throw new MoxoException(ResponseCode.P007, "Post not found for given id");
        } catch (Exception e) {
            log.error("error while getting posts : ", e);
            throw new MoxoException(ResponseCode.P007, e.getMessage(), e);
        }
    }

    @Override
    public List<PawPostResponse> fetchPaginatedPost(String pageId, String size) {
        try {
            List<PawPostEntity> entities = pawPostRepository.findAll();
            if (!entities.isEmpty()) {
                return entities.stream()
                        .map(pawPostEntity -> modelMapper.map(pawPostEntity, PawPostResponse.class))
                        .collect(Collectors.toList());
            }
            throw new MoxoException(ResponseCode.P007, "No post found!");
        } catch (Exception e) {
            log.error("error while getting posts : ", e);
            throw new MoxoException(ResponseCode.P007, e.getMessage(), e);
        }
    }
}
