package com.moxo.app.dto.paw;

import com.moxo.app.entity.paw.FosterDetailEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FosterDetails {

    private String id;
    private String name;
    private String address;
    private LatLng latLong;
    private List<Services> services;
    private List<Reviews> reviews;
    private String msisdn;
    private String email;

    public FosterDetails(FosterDetailEntity entity) {
        id= entity.getId();
        name = entity.getName();
        address = entity.getAddress();
        latLong = entity.getLatLong();
        services = entity.getServices();
        reviews = entity.getReviews();
        msisdn = entity.getMsisdn();
        email = entity.getEmail();
    }
}
