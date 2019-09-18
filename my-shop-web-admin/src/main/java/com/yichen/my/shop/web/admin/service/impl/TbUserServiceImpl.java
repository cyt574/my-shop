package com.yichen.my.shop.web.admin.service.impl;

import com.yichen.my.shop.commons.dto.BaseResult;
import com.yichen.my.shop.commons.dto.PageInfo;
import com.yichen.my.shop.commons.utils.RegexpUtils;
import com.yichen.my.shop.domain.TbUser;
import com.yichen.my.shop.web.admin.dao.TbUserDao;
import com.yichen.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    TbUserDao tbUserDao;

    @Override
    public List<TbUser> selectAll() {
        return tbUserDao.selectAll();
    }

    @Override
    public BaseResult save(TbUser tbUser) {
        BaseResult baseResult = checkTbUser(tbUser);
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
            tbUser.setUpdated(new Date());
            if (tbUser.getId() == null) {
                tbUser.setCreated(new Date());
                tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
                tbUserDao.insert(tbUser);
            } else {
                tbUserDao.update(tbUser);
            }
            baseResult.setMessage("用户信息保存成功");
        }
        return baseResult;
    }

    @Override
    public void delete(Long id) {
        tbUserDao.delete(id);
    }

    @Override
    public TbUser getById(Long id) {
        return tbUserDao.getById(id);
    }

    @Override
    public void update(TbUser tbUser) {
        tbUserDao.update(tbUser);
    }


    @Override
    public TbUser login(String email, String password) {
        TbUser tbUser = tbUserDao.getByEmail(email);
        if (tbUser != null) {
            String encodedPassword = DigestUtils.md5DigestAsHex(password.getBytes());
            if (encodedPassword.equals(tbUser.getPassword())) {
                return tbUser;
            }
        }
        return null;
    }

    @Override
    public void deleteMulti(String[] id) {
        tbUserDao.deleteMulti(id);
    }

    @Override
    public PageInfo<TbUser> page(int start, int length, int draw, TbUser tbUser) {
        int count = tbUserDao.count(tbUser);
        PageInfo<TbUser> pageInfo = new PageInfo<>();

        Map<String, Object> params = new HashMap<>();
        params.put("start", start);
        params.put("length", length);
        params.put("tbUser", tbUser);

        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(tbUserDao.page(params));
        return pageInfo;
    }

    @Override
    public Integer count(TbUser tbUser) {
        return tbUserDao.count(tbUser);
    }

    private BaseResult checkTbUser(TbUser tbUser) {
        BaseResult baseResult = null;
        if (StringUtils.isBlank(tbUser.getUsername())) {
            baseResult = BaseResult.fail("姓名不能为空，请重新输入");
        } else if (StringUtils.isBlank(tbUser.getPassword())) {
            baseResult = BaseResult.fail("密码不能为空，请重新输入");
        } else if (StringUtils.isBlank(tbUser.getEmail())) {
            baseResult = BaseResult.fail("邮箱不能为空，请重新输入");
        } else if (!RegexpUtils.checkEmail(tbUser.getEmail())) {
            baseResult = BaseResult.fail("邮箱格式错误，请重新输入");
        } else if (StringUtils.isBlank(tbUser.getPhone())) {
            baseResult = BaseResult.fail("手机号不能为空，请重新输入");
        } else if (!RegexpUtils.checkPhone(tbUser.getPhone())) {
            baseResult = BaseResult.fail("手机号格式错误，请重新输入");
        } else {
                baseResult = BaseResult.success();
        }

        return baseResult;
    }
}
