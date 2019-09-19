package com.yichen.my.shop.web.admin.abstracts;

import com.yichen.my.shop.commons.dto.PageInfo;
import com.yichen.my.shop.commons.persistence.BaseDao;
import com.yichen.my.shop.commons.persistence.BaseEntity;
import com.yichen.my.shop.commons.persistence.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractBaseServiceImpl<T extends BaseEntity, D extends BaseDao<T>>  implements BaseService<T> {
    @Autowired
    protected D dao;

    @Override
    public List<T> selectAll() {
        return dao.selectAll();
    };

    @Override
    public void delete(Long id) {
        dao.delete(id);
    };

    @Override
    public T getById(Long id) {
        return dao.getById(id);
    }
    @Override
    public void update(T entity) {
        dao.update(entity);
    };
    @Override
    public void deleteMulti(String[] id) {
        dao.deleteMulti(id);
    };
    @Override
    public Integer count(T entity) {
        return dao.count(entity);
    };


    //    @Override
//    public void insert(T entity) {
//        dao.insert(entity);
//    };

    public List<T> page(Map<String, Object> map){
        return dao.page(map);
    };

    public PageInfo<T> page(int start, int length, int draw, T entity) {
        int count = count(entity);
        PageInfo<T> pageInfo = new PageInfo<>();

        Map<String, Object> params = new HashMap<>();
        params.put("start", start);
        params.put("length", length);
        params.put("pageParams", entity);

        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(page(params));
        return pageInfo;
    }

}
