package com.itheima.interceptor;

import com.itheima.pojo.TUser;
import lombok.SneakyThrows;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author wangweili
 * 登录权限过滤器
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * @param request  请求
     * @param response 响应
     * @param handler  处理
     * @return 判断是否放行
     */
    @Override
    @SneakyThrows
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String url = request.getRequestURI();
        Logger logger = Logger.getLogger(LoginInterceptor.class);
        logger.info("开始权限校验...");
        String loginUrl = "/login.jsp";
        String loginPauseUrl = "/user/login";
        if (url.contains(loginUrl) || url.contains(loginPauseUrl)) {
            return true;
        }
        HttpSession session = request.getSession();
        TUser loginUser = (TUser) session.getAttribute("LOGIN_USER");
        if (loginUser != null) {
            logger.info("权限校验成功");
            return true;
        }
        logger.info("权限校验失败!");
        response.sendRedirect("/login.jsp");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
