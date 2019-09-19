package com.yichen.my.shop.web.admin.web.controller;


import com.yichen.my.shop.commons.dto.BaseResult;
import com.yichen.my.shop.domain.TbContentCategory;
import com.yichen.my.shop.web.admin.abstracts.AbstractBaseTreeController;
import com.yichen.my.shop.web.admin.service.TbContentCategoryService;
import com.yichen.my.shop.web.admin.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController extends AbstractBaseTreeController<TbContentCategory, TbContentCategoryService> {

    @ModelAttribute
    public TbContentCategory  tbContentCategory(Long id) {
        TbContentCategory tbContentCategory;
        if( id != null) {
            tbContentCategory = service.getById(id);
        } else {
            tbContentCategory = new TbContentCategory();
        }
        return tbContentCategory;
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<TbContentCategory> targetList  = new ArrayList<>();
        List<TbContentCategory> sourceList = service.selectAll();
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
        return service.selectByPid(id);
    }

    @Override
    @PostMapping("/save")
    public String  save(TbContentCategory tbContentCategory, RedirectAttributes redirectAttributes, Model model) {
        BaseResult baseResult = service.save(tbContentCategory);

        if( baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/content/category/list";
        } else {
            model.addAttribute("baseResult", baseResult);
            return form(tbContentCategory);
        }
    }

    @Override
    @GetMapping("/form")
    public String form(TbContentCategory tbContentCategory) {
        return "content_category_form";
    }

}
