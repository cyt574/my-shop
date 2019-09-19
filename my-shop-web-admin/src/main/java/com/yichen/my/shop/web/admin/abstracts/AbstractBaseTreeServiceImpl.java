package com.yichen.my.shop.web.admin.abstracts;

import com.yichen.my.shop.commons.persistence.BaseEntity;
import com.yichen.my.shop.commons.persistence.BaseTreeDao;
import com.yichen.my.shop.commons.persistence.BaseTreeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class  AbstractBaseTreeServiceImpl<T extends BaseEntity, D extends BaseTreeDao<T>> implements BaseTreeService<T> {

    @Autowired
    protected D dao;

    public List<T> selectAll() {
        return dao.selectAll();
    }

    @Override
    public List<T> selectByPid(Long parentId) {
        return dao.selectByPid(parentId);
    }

    @Override
    public T getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public void update(T entity) {
        dao.update(entity);
    }

    @Override
    public void deleteMulti(String[] id) {
        dao.deleteMulti(id);
    }

    @Override
    public Integer count(T entity) {
        return dao.count(entity);
    }


    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    public void insert(T entity) {
        dao.insert(entity);
    }
}
