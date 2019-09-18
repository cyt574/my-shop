package com.yichen.my.shop.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yichen.my.shop.commons.persistence.BaseEntity;
import lombok.Data;

@Data
public class TbContentCategory extends BaseEntity {
    private Long parentId;
    private String name;
    private Integer status;
    private Integer sortOrder;
    @JsonProperty(value = "isParent")
    private Boolean isParent;
}
