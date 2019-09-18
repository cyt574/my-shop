package com.yichen.my.shop.web.admin.service.impl;

import com.yichen.my.shop.domain.TbContentCategory;
import com.yichen.my.shop.web.admin.dao.TbContentCategoryDao;
import com.yichen.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService {
    @Autowired
    TbContentCategoryDao tbContentCategoryDao;

    @Override
    public List<TbContentCategory> selectAll() {
        return tbContentCategoryDao.selectAll();
    }

    @Override
    public List<TbContentCategory> selectByPid(Long parentId) {
        return tbContentCategoryDao.selectByPid(parentId);
    }
}
