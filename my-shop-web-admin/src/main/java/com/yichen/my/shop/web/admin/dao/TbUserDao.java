package com.yichen.my.shop.web.admin.dao;

import com.yichen.my.shop.commons.persistence.BaseDao;
import com.yichen.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

@Repository
public interface TbUserDao extends BaseDao<TbUser> {
    TbUser getByEmail(String email);
}
