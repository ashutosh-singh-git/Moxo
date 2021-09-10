package com.moxo.app.repository;

import com.moxo.app.entity.FosterDetailEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FosterRepository extends MongoRepository<FosterDetailEntity, String> {
    @Query("{ 'id' : ?0 }")
    Optional<FosterDetailEntity> findById(String id);

}
