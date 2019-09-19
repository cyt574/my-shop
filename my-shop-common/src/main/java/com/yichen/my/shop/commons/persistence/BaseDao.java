package com.yichen.my.shop.commons.persistence;

import java.util.List;
import java.util.Map;

public interface BaseDao<T extends BaseEntity> {
    /**
     *
     * 查询用户全部信息
     * @return
     */
    List<T> selectAll();

    void insert(T entity);

    void delete(Long id);

    T getById(Long id);

    void update(T entity);

    void deleteMulti(String[] id);

    List<T> page(Map<String, Object> map);

    Integer count(T entity);
}
