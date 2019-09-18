package com.yichen.my.shop.web.admin.service;

import com.yichen.my.shop.commons.dto.BaseResult;
import com.yichen.my.shop.commons.dto.PageInfo;
import com.yichen.my.shop.domain.TbUser;

import java.util.List;

public interface TbUserService {
    List<TbUser> selectAll();

    BaseResult save(TbUser tbUser);

    void delete(Long id);

    TbUser getById(Long id);

    void update(TbUser tbUser);

    TbUser login(String email, String password);

    void deleteMulti(String[] id);

    PageInfo<TbUser> page(int start, int length, int draw, TbUser tbUser);

    Integer count(TbUser tbUser);

}
