package com.moxo.app.dto.paw;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
public class UserProfile {
    private String id;
    private String name;
    private String msisdn;
    private String email;
    private String about;
    private String profileImage;
    private Map<MediaType, Set<String>> gallery;
    private String rating;
    private Location location;
    private String shareLink;
    private List<Review> reviews;
    private List<Tasks> tasks;
    private List<VerificationAttributes> verification;
    private List<Services> services;
    private List<AcceptedPayment> paymentMethods;
}
