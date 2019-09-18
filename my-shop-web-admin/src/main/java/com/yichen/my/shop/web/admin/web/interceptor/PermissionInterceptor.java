package com.yichen.my.shop.web.admin.web.interceptor;

import com.yichen.my.shop.commons.constant.ConstantUtils;
import com.yichen.my.shop.domain.TbUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PermissionInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if(modelAndView != null){
            String viewName = modelAndView.getViewName();
            if(StringUtils.isNotBlank(viewName) && viewName.endsWith("login")) {
                TbUser user = (TbUser) request.getSession().getAttribute(ConstantUtils.SESSION_USER);
                if(user != null) {
                    response.sendRedirect("/main");
                }
            }
        }
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
