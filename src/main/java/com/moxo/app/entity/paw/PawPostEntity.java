package com.moxo.app.entity.paw;

import com.moxo.app.dto.paw.FosterDetails;
import com.moxo.app.dto.paw.LatLng;
import com.moxo.app.dto.paw.Location;
import com.moxo.app.dto.paw.Reviews;
import com.moxo.app.dto.paw.Services;
import com.moxo.app.entity.BaseEntity;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document(collection = "post")
@Getter
@Setter
@NoArgsConstructor
public class PawPostEntity extends BaseEntity {

    @Id
    @Generated
    private String id;
    private String title;
    private Location location;
    private String userId;
    private String img;
    private Map<String, String> meta;

}
