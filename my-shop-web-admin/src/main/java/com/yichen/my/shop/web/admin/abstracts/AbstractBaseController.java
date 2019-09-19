package com.yichen.my.shop.web.admin.abstracts;

import com.yichen.my.shop.commons.dto.BaseResult;
import com.yichen.my.shop.commons.dto.PageInfo;
import com.yichen.my.shop.commons.persistence.BaseEntity;
import com.yichen.my.shop.commons.persistence.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractBaseController<T extends BaseEntity, S extends BaseService<T>> {

    @Autowired
    protected S service;

    public abstract String list();

    public abstract String form();

    public abstract String save(T entity, RedirectAttributes redirectAttributes, Model model);

    public abstract BaseResult delete(String ids);

    public abstract PageInfo<T> page(HttpServletRequest request, T entity);

    public abstract String detail(T entity);

}
