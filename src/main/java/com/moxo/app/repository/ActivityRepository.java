package com.moxo.app.repository;

import com.moxo.app.entity.ActivityEntity;
import com.moxo.app.entity.FeedEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends MongoRepository<ActivityEntity, String> {

    Slice<FeedEntity> findAllByStateTrue(Pageable pageable);

    Slice<FeedEntity> findAllByStateTrue(TextCriteria textCriteria, Pageable pageable);
}
