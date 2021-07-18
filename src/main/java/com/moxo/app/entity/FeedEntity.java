package com.moxo.app.entity;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.TextScore;

import java.util.List;

@Document(collection = "feed")
@Getter
@Setter
public class FeedEntity extends BaseEntity {

    @Id
    @Generated
    private String id;

    @TextIndexed(weight = 4)
    private String title;

    @Indexed(unique=true)
    private String link;

    @TextIndexed(weight = 3)
    private String description;

    @TextScore
    private Double score;

    private Long publishedAt;

    private String img;

    private List<String> cat;

    @TextIndexed(weight = 1)
    private String author;

    @TextIndexed(weight = 2)
    private String publisher;

    private boolean state;

}
