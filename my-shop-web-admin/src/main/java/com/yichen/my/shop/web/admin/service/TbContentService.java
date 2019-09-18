package com.yichen.my.shop.web.admin.service;

import com.yichen.my.shop.commons.dto.BaseResult;
import com.yichen.my.shop.commons.dto.PageInfo;
import com.yichen.my.shop.domain.TbContent;

import java.util.List;


public interface TbContentService {
    List<TbContent> selectAll();

    BaseResult save(TbContent tbContent);

    void delete(Long id);

    TbContent getById(Long id);

    void update(TbContent tbContent);

    void deleteMulti(String[] id);

    PageInfo<TbContent> page(int start, int length, int draw, TbContent tbContent) ;

    Integer count(TbContent tbContent);

}
