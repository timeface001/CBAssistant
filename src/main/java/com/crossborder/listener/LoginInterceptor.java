package com.crossborder.listener;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;


/**
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    //日志
    protected Logger log = Logger.getLogger(getClass());

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handle) throws Exception {
        String requestUri = request.getRequestURI();
        if (requestUri.contains("/common/login") ||
                requestUri.contains("/assistant/index/login.jsp") ||
                requestUri.equals("/CBAssistant") ||
                requestUri.contains("/assistant/lib/") ||
                requestUri.contains("/assistant/static/") ||
                requestUri.contains("/assistant/common/") || requestUri.contains("public/category/init")) {
            return true;
        }
        //创建session
        HttpSession session = request.getSession();
        if (session == null || session.equals("")) {
            if (request.getHeader("x-requested-with") != null
                    && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                response.addHeader("sessionstatus", "timeOut");
            } else {
                response.sendRedirect(request.getContextPath() + "/assistant/index/login.jsp");
            }
            return false;
        }
        //获得session中的用户
        Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
        if (user == null) {
            if (request.getHeader("x-requested-with") != null
                    && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                response.addHeader("sessionstatus", "timeOut");
            } else {
                response.sendRedirect(request.getContextPath() + "/assistant/index/login.jsp");
            }
            return false;
        }
        return true;
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

    //前台弹出alert框
    public void toAlert(HttpServletResponse response) {
        try {
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            OutputStreamWriter out = new OutputStreamWriter(response.getOutputStream());
            String msg = "由于您长时间没有操作，session已过期，请重新登录！";
            msg = new String(msg.getBytes("UTF-8"));
            out.write("<meta http-equiv='Content-Type' content='text/html';charset='UTF-8'>");
            out.write("<script>");
            out.write("alert('" + msg + "');");
            out.write("top.location.href = '/assistant/index/login.jsp'");
            out.write("</script>");
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}