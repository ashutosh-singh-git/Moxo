package com.moxo.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@ApiModel
public class PageableResponse<T> {

    @ApiModelProperty(value = "true if content is empty")
    private final boolean empty;
    @ApiModelProperty(value = "true if this is the first page")
    private final boolean first;
    @ApiModelProperty(value = "true if this is the last page")
    private final boolean last;
    @ApiModelProperty(value = "List of contents")
    private final List<T> content;
    private final int number;
    @ApiModelProperty(value = "Total size of data")
    private final int size;
}
