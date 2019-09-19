package com.yichen.my.shop.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yichen.my.shop.commons.persistence.BaseTreeEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class TbContentCategory extends BaseTreeEntity {
//    private Long parentId;

    @Length(min = 1, max = 20, message = "品类名称必须介于1到20之间")
    private String name;
    private Integer status;
    @NotNull(message = "排序不能为空")
    private Integer sortOrder;
    @JsonProperty(value = "isParent")
    private Boolean isParent;
    private TbContentCategory parent;
}
