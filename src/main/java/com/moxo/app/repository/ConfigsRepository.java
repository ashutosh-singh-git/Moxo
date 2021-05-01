package com.moxo.app.repository;

import com.moxo.app.entity.ConfigEntity;
import com.moxo.app.entity.FeedEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigsRepository extends MongoRepository<ConfigEntity, String> {

    List<ConfigEntity> findAllByStateTrue();

}
