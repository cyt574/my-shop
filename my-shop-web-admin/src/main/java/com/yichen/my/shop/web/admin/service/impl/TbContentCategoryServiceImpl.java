package com.yichen.my.shop.web.admin.service.impl;

import com.yichen.my.shop.commons.dto.BaseResult;
import com.yichen.my.shop.commons.dto.PageInfo;
import com.yichen.my.shop.commons.validator.BeanValidator;
import com.yichen.my.shop.domain.TbContentCategory;
import com.yichen.my.shop.web.admin.abstracts.AbstractBaseTreeServiceImpl;
import com.yichen.my.shop.web.admin.dao.TbContentCategoryDao;
import com.yichen.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TbContentCategoryServiceImpl extends AbstractBaseTreeServiceImpl<TbContentCategory, TbContentCategoryDao> implements TbContentCategoryService {

    @Override
    public BaseResult save(TbContentCategory tbContentCategory) {
        String validator = BeanValidator.validator(tbContentCategory);
        if ( validator == null) {
            TbContentCategory parent = tbContentCategory.getParent();
            if( parent == null || parent.getId() == null) {
                parent.setId(0L);
                parent.setIsParent(true);
            }

            tbContentCategory.setUpdated(new Date());

            if (tbContentCategory.getId() == null) {
                tbContentCategory.setCreated(new Date());
                tbContentCategory.setIsParent(false);

                if(parent.getId() != 0L ){
                    parent = getById(parent.getId());
                    if( parent != null) {
                        parent.setIsParent(true);
                        update(parent);
                    }
                }

                insert(tbContentCategory);
            } else {
                update(tbContentCategory);
            }
            return BaseResult.success();
        } else {
            return BaseResult.fail(validator);
        }
    }
}
