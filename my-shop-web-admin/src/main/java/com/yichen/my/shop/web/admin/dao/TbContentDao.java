package com.yichen.my.shop.web.admin.dao;

import com.yichen.my.shop.domain.TbContent;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TbContentDao {

    List<TbContent> selectAll();

    void insert(TbContent tbContent);

    void delete(Long id);

    TbContent getById(Long id);

    void update(TbContent tbContent);

    void deleteMulti(String[] id);

    List<TbContent> page(Map<String, Object> map);

    Integer count(TbContent tbContent);
}
