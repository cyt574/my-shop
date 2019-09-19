package com.yichen.my.shop.commons.persistence;

import com.yichen.my.shop.commons.dto.BaseResult;
import com.yichen.my.shop.commons.dto.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BaseService<T extends BaseEntity> {
    List<T> selectAll();

    BaseResult save(T entity);

    void delete(Long id);

    T getById(Long id);

    void update(T entity);


    void deleteMulti(String[] id);

    PageInfo<T> page(int start, int length, int draw, T entity);

    Integer count(T entity);
}
