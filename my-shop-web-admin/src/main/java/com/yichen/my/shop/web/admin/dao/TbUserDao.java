package com.yichen.my.shop.web.admin.dao;

import com.yichen.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TbUserDao {

    /**
     *
     * 查询用户全部信息
     * @return
     */
    List<TbUser> selectAll();

    void insert(TbUser tbUser);

    void delete(Long id);

    TbUser getById(Long id);

    void update(TbUser tbUser);

    TbUser getByEmail(String email);

    void deleteMulti(String[] id);

    List<TbUser> page(Map<String, Object> map);

    Integer count(TbUser tbUser);
}
