package com.thc.watchapi.config.interceptor;

import com.thc.watchapi.config.redis.RedisConstants;
import com.thc.watchapi.exception.MyException;
import com.thc.watchapi.model.Admin;
import com.thc.watchapi.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.config.interceptor
 * @Description: Token拦截器
 * @date 2020/11/12 5:09 下午
 */
public class WebTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;

    @Value("${request.token.check}")
    private Boolean tokenCheck;

    private static String msg = "Token验证失败，请先登录!";

    private static String msg1 = "登录信息已失效，请重新登录!";

    private static Integer code = 1001;

    /**
     * 进入controller方法之前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (!tokenCheck) {
            return true;
        }
        String token = request.getHeader("Authorization");
        if (token == "") {
            throw new MyException(code, msg);
        }
        Admin admin = (Admin) redisUtil.get(token, RedisConstants.datebase2);
        if (admin == null) {
            throw new MyException(code, msg1);
        }
        request.setAttribute("currentUser", admin);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    /**
     * 调用完controller之后，视图渲染之前
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 整个完成之后，通常用于资源清理
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

}

