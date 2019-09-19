package com.yichen.my.shop.commons.persistence;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BaseTreeDao<T extends BaseEntity> {
    List<T> selectAll();

    void insert(T entity);

    void delete(Long id);

    T getById(Long id);

    void update(T entity);

    void deleteMulti(String[] id);

    List<T> page(Map<String, Object> map);

    Integer count(T entity);

    List<T> selectByPid(Long pid);
}
