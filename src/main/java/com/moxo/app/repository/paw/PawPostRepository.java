package com.moxo.app.repository.paw;

import com.moxo.app.entity.paw.PawPostEntity;
import com.moxo.app.entity.paw.PawUserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PawPostRepository extends MongoRepository<PawPostEntity, String> {
    @Query("{ 'id' : ?0 }")
    Optional<PawPostEntity> findById(String id);

//    List<PawUserEntity> findAll(String email);
}
