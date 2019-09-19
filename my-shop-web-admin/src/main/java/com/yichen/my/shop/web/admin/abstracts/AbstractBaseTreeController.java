package com.yichen.my.shop.web.admin.abstracts;

import com.yichen.my.shop.commons.dto.PageInfo;
import com.yichen.my.shop.commons.persistence.BaseEntity;
import com.yichen.my.shop.commons.persistence.BaseService;
import com.yichen.my.shop.commons.persistence.BaseTreeEntity;
import com.yichen.my.shop.commons.persistence.BaseTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public abstract class AbstractBaseTreeController<T extends BaseTreeEntity, S extends BaseTreeService<T>>{

    @Autowired
    protected S service;

    public abstract String list(Model model);

    public abstract String form(T entity);

    public abstract String save(T entity, RedirectAttributes redirectAttributes, Model models);

    protected void sortList(List<T> sourceEntity, List<T> targetEntity, Long parentId) {
        for (T entity : sourceEntity) {
            if(entity.getParent().getId().equals(parentId)) {
                targetEntity.add(entity);
                if(entity.getIsParent()) {
                    sortList(sourceEntity, targetEntity, entity.getId());
                }
            }
        }
    }
}
