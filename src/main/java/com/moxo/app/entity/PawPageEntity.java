package com.moxo.app.entity;

import com.moxo.app.dto.paw.PageContent;
import com.moxo.app.dto.paw.PageType;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "paw_page")
@Getter
@Setter
public class PawPageEntity extends BaseEntity {

    @Id
    @Generated
    private String id;
    private PageType pageType;
    private List<PageContent> contents;
}
