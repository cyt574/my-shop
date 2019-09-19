package com.yichen.my.shop.web.admin.service.impl;

import com.yichen.my.shop.commons.dto.BaseResult;
import com.yichen.my.shop.commons.dto.PageInfo;
import com.yichen.my.shop.commons.utils.RegexpUtils;
import com.yichen.my.shop.commons.validator.BeanValidator;
import com.yichen.my.shop.domain.TbContent;
import com.yichen.my.shop.web.admin.abstracts.AbstractBaseServiceImpl;
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
public class TbContentSerivceImpl extends AbstractBaseServiceImpl<TbContent, TbContentDao> implements TbContentService {
    @Override
    public BaseResult save(TbContent tbContent) {
        String validator = BeanValidator.validator(tbContent);
        if( validator != null) {
            return BaseResult.fail(validator);
        } else {
            tbContent.setUpdated(new Date());
            if (tbContent.getId() == null) {
                tbContent.setCreated(new Date());
                dao.insert(tbContent);
            } else {
                tbContent.setUpdated(new Date());
                update(tbContent);
            }
            return BaseResult.success();
        }
    }
}
