package com.yichen.my.shop.web.admin.service;

import com.yichen.my.shop.domain.TbContentCategory;

import java.util.List;

public interface TbContentCategoryService {

    List<TbContentCategory> selectAll();

    List<TbContentCategory> selectByPid(Long parentId);
}
