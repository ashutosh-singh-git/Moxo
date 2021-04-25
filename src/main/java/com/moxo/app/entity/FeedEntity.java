package com.moxo.app.entity;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Persistent;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.TextScore;

import java.util.List;

@Document(collection = "feed")
@Getter
@Setter
public class FeedEntity {

    @Id
    @Generated
    private String id;

    @TextIndexed(weight = 10)
    private String title;

    @Indexed(unique=true)
    private String link;

    @TextIndexed(weight = 10)
    private String description;

    @TextScore
    private Double score;

    private Long publishedAt;

    private String img;

    private List<String> cat;

    private String author;

    @CreatedDate
    private Long createdAt;

    @LastModifiedDate
    private Long updatedAt;

    private boolean state;

    @Version
    private Long version;
}
