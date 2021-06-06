package com.moxo.app.repository;

import com.moxo.app.dto.State;
import com.moxo.app.entity.ActivityEntity;
import com.moxo.app.entity.FeedEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends MongoRepository<ActivityEntity, String> {

    Slice<ActivityEntity> findAllByStateEquals(State state, Pageable pageable);

    Slice<ActivityEntity> findAllBy(TextCriteria textCriteria, Pageable pageable);
}
