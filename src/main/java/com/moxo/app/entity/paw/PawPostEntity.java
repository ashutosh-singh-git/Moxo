package com.moxo.app.entity.paw;

import com.moxo.app.dto.paw.Location;
import com.moxo.app.dto.paw.PostType;
import com.moxo.app.dto.paw.PostUser;
import com.moxo.app.entity.BaseEntity;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "post")
@Getter
@Setter
@NoArgsConstructor
public class PawPostEntity extends BaseEntity {

    @Id
    @Generated
    private String id;
    private PostUser user;
    private String desc;
    private String img;
    private Location location;
    private PostType type;
    private Map<String, String> meta;
}
