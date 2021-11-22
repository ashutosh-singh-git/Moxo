package com.moxo.app.dto.paw;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;


@Data
@EqualsAndHashCode(callSuper = true)
public class PawPostResponse extends PawPost {
    private String id;
    private Long createdAt;
    private Long updatedAt;
}
