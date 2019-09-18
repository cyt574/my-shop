package com.yichen.my.shop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yichen.my.shop.commons.persistence.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class TbUser extends BaseEntity {
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private String phone;
    private String email;
    private Date created;
    private Date updated;
}
