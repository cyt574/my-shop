package com.yichen.my.shop.web.admin.service.impl;

import com.yichen.my.shop.commons.dto.BaseResult;
import com.yichen.my.shop.commons.dto.PageInfo;
import com.yichen.my.shop.commons.utils.RegexpUtils;
import com.yichen.my.shop.commons.validator.BeanValidator;
import com.yichen.my.shop.domain.TbUser;
import com.yichen.my.shop.web.admin.abstracts.AbstractBaseServiceImpl;
import com.yichen.my.shop.web.admin.dao.TbUserDao;
import com.yichen.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TbUserServiceImpl extends AbstractBaseServiceImpl<TbUser, TbUserDao> implements TbUserService {

    @Override
    public BaseResult save(TbUser tbUser) {
        String validator = BeanValidator.validator(tbUser);
        if( validator != null) {
            return BaseResult.fail(validator);
        } else {
            tbUser.setUpdated(new Date());
            if (tbUser.getId() == null) {
                tbUser.setCreated(new Date());
                tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
                dao.insert(tbUser);
            } else {
                tbUser.setUpdated(new Date());
                update(tbUser);
            }
            return BaseResult.success();
        }
    }

    @Override
    public TbUser login(String email, String password) {
        TbUser tbUser = dao.getByEmail(email);
        if (tbUser != null) {
            String encodedPassword = DigestUtils.md5DigestAsHex(password.getBytes());
            if (encodedPassword.equals(tbUser.getPassword())) {
                return tbUser;
            }
        }
        return null;
    }
}
