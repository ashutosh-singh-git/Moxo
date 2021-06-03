package com.moxo.app.entity;

import com.moxo.app.dto.ActivityMedia;
import com.moxo.app.dto.ActivityUser;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collation = "activity")
@Getter
@Setter
public class ActivityEntity extends BaseEntity {

    @Id
    @Generated
    private String id;
    private String title;
    private String description;
    private ActivityMedia media;
    private ActivityUser user;
    private int likes;
    private int dislikes;
    private List<String> comments;
    private int ranking;
}
