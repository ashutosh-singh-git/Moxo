package com.moxo.app.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ActivityMedia {

    private ActivityType type;
    private String link;
    private String caption;
}
