package com.yichen.my.shop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yichen.my.shop.commons.persistence.BaseEntity;
import com.yichen.my.shop.commons.utils.RegexpUtils;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class TbUser extends BaseEntity {
    @Length(min = 6, max = 20, message = "姓名长度必须介于6 - 20位之间")
    private String username;
    @JsonIgnore
    @Length(min = 6, max = 20, message = "密码长度必须介于6 - 20位之间")
    private String password;

    @Pattern(regexp = RegexpUtils.PHONE, message = "手机号格式不正确")
    private String phone;
    @Pattern(regexp = RegexpUtils.EMAIL, message = "邮箱格式不正确")
    private String email;
}
