package com.yichen.my.shop.web.admin.dao;

import com.yichen.my.shop.domain.TbContentCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbContentCategoryDao {
    List<TbContentCategory> selectAll();
    List<TbContentCategory> selectByPid(@Param("parentId") Long pid);
}
