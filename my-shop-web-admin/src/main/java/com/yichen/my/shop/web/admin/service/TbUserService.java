package com.yichen.my.shop.web.admin.service;
import com.yichen.my.shop.commons.persistence.BaseService;
import com.yichen.my.shop.domain.TbUser;


public interface TbUserService extends BaseService<TbUser>{
    TbUser login(String email, String password);
}
