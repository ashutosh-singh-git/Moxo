package com.moxo.app.repository.paw;

import com.moxo.app.entity.paw.PawConfigEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PawConfigRepository extends MongoRepository<PawConfigEntity, String> {

    Optional<PawConfigEntity> findByOsAndBn(String os, String bn);

}
