package com.moxo.app.entity.paw;

import com.moxo.app.dto.paw.AcceptedPayment;
import com.moxo.app.dto.paw.Location;
import com.moxo.app.dto.paw.MediaType;
import com.moxo.app.dto.paw.PawUser;
import com.moxo.app.dto.paw.Review;
import com.moxo.app.dto.paw.Services;
import com.moxo.app.dto.paw.Tasks;
import com.moxo.app.dto.paw.VerificationAttributes;
import com.moxo.app.entity.BaseEntity;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Document(collection = "paw_user")
@Getter
@Setter
@NoArgsConstructor
public class PawUserEntity extends BaseEntity {

    @Id
    @Generated
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
    private List<Tasks> completedTasks;
    private VerificationAttributes verification;
    private List<Services> services;
    private List<AcceptedPayment> payments;

    public PawUserEntity(PawUser pawUser) {
        this.name = pawUser.getName();
        this.msisdn = pawUser.getMsisdn();
        this.email = pawUser.getEmail();
    }
}
