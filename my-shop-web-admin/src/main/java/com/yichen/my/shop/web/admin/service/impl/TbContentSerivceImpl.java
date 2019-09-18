package com.yichen.my.shop.web.admin.service.impl;

import com.yichen.my.shop.commons.dto.BaseResult;
import com.yichen.my.shop.commons.dto.PageInfo;
import com.yichen.my.shop.commons.utils.RegexpUtils;
import com.yichen.my.shop.domain.TbContent;
import com.yichen.my.shop.web.admin.dao.TbContentDao;
import com.yichen.my.shop.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbContentSerivceImpl implements TbContentService {
    @Autowired
    TbContentDao tbContentDao;

    @Override
    public List<TbContent> selectAll() {
        return tbContentDao.selectAll();
    }

    @Override
    public BaseResult save(TbContent tbContent) {
        BaseResult baseResult = checkTbContent(tbContent);
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
            tbContent.setUpdated(new Date());
            if (tbContent.getId() == null) {
                tbContentDao.insert(tbContent);
            } else {
                tbContentDao.update(tbContent);
            }
            baseResult.setMessage("用户信息保存成功");
        }
        return baseResult;
    }

    private BaseResult checkTbContent(TbContent tbContent) {
        BaseResult baseResult = null;
        if (tbContent.getCategoryId() == null) {
            baseResult = BaseResult.fail("内容所属分类不能为空，请重新输入");
        } else if (StringUtils.isBlank(tbContent.getTitle())) {
            baseResult = BaseResult.fail("标题不能为空，请重新输入");
        } else if (StringUtils.isBlank(tbContent.getContent())) {
            baseResult = BaseResult.fail("内容不能为空，请重新输入");
        } else if (!RegexpUtils.checkEmail(tbContent.getPic())) {
            baseResult = BaseResult.fail("图片不能为空，请重新输入");
        } else if (StringUtils.isBlank(tbContent.getSubTitle())) {
            baseResult = BaseResult.fail("子标题不能为空，请重新输入");
        } else if (!RegexpUtils.checkPhone(tbContent.getTitleDesc())) {
            baseResult = BaseResult.fail("描述不能为空，请重新输入");
        } else {
            baseResult = BaseResult.success();
        }
        return baseResult;
    }

    @Override
    public void delete(Long id) {
        tbContentDao.delete(id);
    }

    @Override
    public TbContent getById(Long id) {
        return tbContentDao.getById(id);
    }

    @Override
    public void update(TbContent tbContent) {
        tbContentDao.update(tbContent);
    }

    @Override
    public void deleteMulti(String[] id) {
        tbContentDao.deleteMulti(id);
    }

    @Override
    public PageInfo<TbContent> page(int start, int length, int draw, TbContent tbContent) {
        int count = tbContentDao.count(tbContent);
        PageInfo<TbContent> pageInfo = new PageInfo<>();

        Map<String, Object> params = new HashMap<>();
        params.put("start", start);
        params.put("length", length);
        params.put("tbContent", tbContent);

        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(tbContentDao.page(params));
        return pageInfo;
    }

    @Override
    public Integer count(TbContent tbContent) {
        return tbContentDao.count(tbContent);
    }
}
