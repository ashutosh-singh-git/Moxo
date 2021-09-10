package com.moxo.app.entity;

import com.moxo.app.dto.paw.FosterDetails;
import com.moxo.app.dto.paw.LatLng;
import com.moxo.app.dto.paw.Reviews;
import com.moxo.app.dto.paw.Services;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "foster")
@Getter
@Setter
public class FosterDetailEntity extends BaseEntity {

    @Id
    @Generated
    private String id;
    private String name;
    private String address;
    private LatLng latLong;
    private List<Services> services;
    private List<Reviews> reviews;
    private String msisdn;
    private String email;

    public FosterDetailEntity(FosterDetails dto) {
        name = dto.getName();
        address = dto.getAddress();
        latLong = dto.getLatLong();
        services = dto.getServices();
        reviews = dto.getReviews();
        msisdn = dto.getMsisdn();
        email = dto.getEmail();
    }
}
