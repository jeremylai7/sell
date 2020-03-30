package com.jeremy.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Auther: laizc
 * @Date: 2020/3/28 21:34
 * @Description:
 */
@Getter
@Setter
public class ViewProduct {
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ViewProductInfo> list;
}
