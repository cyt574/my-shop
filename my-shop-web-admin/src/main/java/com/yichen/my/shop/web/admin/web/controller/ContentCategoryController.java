package com.yichen.my.shop.web.admin.web.controller;


import com.yichen.my.shop.domain.TbContentCategory;
import com.yichen.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

    @Autowired
    TbContentCategoryService tbContentCategoryService;

    @GetMapping("/list")
    public String list(Model model) {
        List<TbContentCategory> targetList  = new ArrayList<>();
        List<TbContentCategory> sourceList = tbContentCategoryService.selectAll();
        sortList(sourceList, targetList, 0L);
        model.addAttribute("tbContentCategories", targetList);
        return "content_category_list";
    }

    @ResponseBody
    @PostMapping("/tree")
    public List<TbContentCategory> treeData(Long id) {
        if( id == null ) {
            id = 0L;
        }
        return tbContentCategoryService.selectByPid(id);
    }

    private void sortList(List<TbContentCategory> sourceList, List<TbContentCategory> targetList, Long parentId) {
        for (TbContentCategory tbContentCategory : sourceList) {
            if(tbContentCategory.getParentId().equals(parentId)) {
                targetList.add(tbContentCategory);
                if(tbContentCategory.getParent()) {
                    sortList(sourceList, targetList, tbContentCategory.getId());
                }
            }
        }
    }

}
