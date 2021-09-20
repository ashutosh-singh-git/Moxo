package com.moxo.app.repository.paw;

import com.moxo.app.entity.paw.FosterDetailEntity;
import com.moxo.app.entity.paw.PawUserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PawUserRepository extends MongoRepository<PawUserEntity, String> {
    @Query("{ 'id' : ?0 }")
    Optional<PawUserEntity> findById(String id);

    Optional<PawUserEntity> findByEmail(String email);
}
