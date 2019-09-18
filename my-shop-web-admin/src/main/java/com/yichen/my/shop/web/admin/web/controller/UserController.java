package com.yichen.my.shop.web.admin.web.controller;

import com.yichen.my.shop.commons.dto.BaseResult;
import com.yichen.my.shop.commons.dto.PageInfo;
import com.yichen.my.shop.domain.TbUser;
import com.yichen.my.shop.web.admin.service.TbUserService;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    TbUserService tbUserService;

    @ModelAttribute
    public TbUser tbUser(Long id) {
        TbUser tbUser = null;
        if( id != null) {
            tbUser = tbUserService.getById(id);
        } else  {
            tbUser = new TbUser();
        }
        return tbUser;
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<TbUser> tbUsers = tbUserService.selectAll();
        model.addAttribute("tbUsers", tbUsers);
        return "user_list";
    }

    @GetMapping("/form")
    public String form() {
        return "user_form";
    }

    @PostMapping("/save")
    public String save(TbUser tbUser, RedirectAttributes redirectAttributes, Model model) {
        BaseResult baseResult = tbUserService.save(tbUser);

        if( baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/user/list";
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
            tbUserService.deleteMulti(idArray);
            baseResult = BaseResult.success("删除数据成功");

        } else {
            baseResult = BaseResult.fail("删除数据失败");
        }
        System.out.println(ids);
        return baseResult;
    }

    @ResponseBody
    @GetMapping("/page")
    public PageInfo<TbUser> page(HttpServletRequest request, TbUser tbUser) {

        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");

        int draw = strDraw == null ? 0: Integer.parseInt(strDraw);
        int start = strStart == null ? 0: Integer.parseInt(strStart);
        int length = strLength == null ? 0: Integer.parseInt(strLength);


        PageInfo<TbUser> tbUsers = tbUserService.page(start, length, draw, tbUser);


        return tbUsers;
    }

    @GetMapping("/detail")
    public String detail(TbUser tbUser) {
        return "user_detail";
    }
}
