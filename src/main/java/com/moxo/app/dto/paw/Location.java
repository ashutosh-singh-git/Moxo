package com.moxo.app.dto.paw;

import lombok.Data;

@Data
public class Location {
    private Float latitude;
    private Float longitude;
    private String address;
    private Integer pincode;
}
