package com.moxo.app.repository;

import com.moxo.app.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {

    UserEntity findByUid(String uid);

    UserEntity findByMsisdn(String msisdn);

    UserEntity findByEmail(String email);

}
