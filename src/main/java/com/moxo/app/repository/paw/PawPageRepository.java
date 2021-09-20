package com.moxo.app.repository.paw;

import com.moxo.app.entity.paw.PawPageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PawPageRepository extends MongoRepository<PawPageEntity, String> {
    @Query("{ 'id' : ?0 }")
    Optional<PawPageEntity> findById(String id);

}
