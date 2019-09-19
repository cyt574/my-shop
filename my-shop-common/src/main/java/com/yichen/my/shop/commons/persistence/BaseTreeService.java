package com.yichen.my.shop.commons.persistence;

import com.yichen.my.shop.commons.dto.BaseResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BaseTreeService<T extends BaseEntity> {
    List<T> selectAll();

    BaseResult save(T entity);

    void delete(Long id);

    T getById(Long id);

    void update(T entity);

    void deleteMulti(String[] id);

    Integer count(T entity);

    List<T> selectByPid(Long pid);
}
