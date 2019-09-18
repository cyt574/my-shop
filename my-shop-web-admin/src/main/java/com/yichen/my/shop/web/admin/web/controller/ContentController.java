package com.yichen.my.shop.web.admin.web.controller;

import com.yichen.my.shop.commons.dto.BaseResult;
import com.yichen.my.shop.commons.dto.PageInfo;
import com.yichen.my.shop.domain.TbContent;
import com.yichen.my.shop.domain.TbUser;
import com.yichen.my.shop.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    TbContentService tbContentService;

    @ModelAttribute
    public TbContent tbContent(Long id) {
        TbContent tbContent = null;
        if( id != null) {
            tbContent = tbContentService.getById(id);
        } else  {
            tbContent = new TbContent();
        }
        return tbContent;
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<TbContent> tbContents = tbContentService.selectAll();
        model.addAttribute("tbContents", tbContents);
        return "content_list";
    }

    @GetMapping("/form")
    public String form() {
        return "content_form";
    }

    @PostMapping("/save")
    public String save(TbContent tbContent, RedirectAttributes redirectAttributes, Model model) {
        BaseResult baseResult = tbContentService.save(tbContent);

        if( baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/content/list";
        } else {
            model.addAttribute("baseResult", baseResult);
            return form();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public BaseResult delete(String ids) {
        BaseResult baseResult = null;
        if (StringUtils.isNotBlank(ids)) {
            String[] idArray = ids.split(",");
            tbContentService.deleteMulti(idArray);
            baseResult = BaseResult.success("删除数据成功");

        } else {
            baseResult = BaseResult.fail("删除数据失败");
        }
        System.out.println(ids);
        return baseResult;
    }

    @ResponseBody
    @GetMapping("/page")
    public PageInfo<TbContent> page(HttpServletRequest request, TbContent tbContent) {

        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");

        int draw = strDraw == null ? 0: Integer.parseInt(strDraw);
        int start = strStart == null ? 0: Integer.parseInt(strStart);
        int length = strLength == null ? 0: Integer.parseInt(strLength);


        PageInfo<TbContent> tbContentPageInfo = tbContentService.page(start, length, draw, tbContent);


        return tbContentPageInfo;
    }

    @GetMapping("/detail")
    public String detail(TbContent tbContent) {
        return "content_detail";
    }

}
