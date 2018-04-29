package com.crossborder.listener;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;


/**
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    //日志
    protected Logger log = Logger.getLogger(getClass());

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handle) throws Exception {
        return true;
        /*String requestUri = request.getRequestURI();
        if (requestUri.equals("/CBAssistant/common/login")) {
            return true;
        }
        //创建session
        HttpSession session = request.getSession();
        if (session == null || session.equals("")) {
            response.sendRedirect(request.getContextPath() + "/assistant/index/login.jsp");
            return false;
        }
        //获得session中的用户
        Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/assistant/index/login.jsp");
            return false;
        }
        return true;*/
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

}