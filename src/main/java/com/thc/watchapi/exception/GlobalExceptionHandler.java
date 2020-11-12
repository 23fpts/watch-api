package com.thc.watchapi.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.exception
 * @Description:
 * @date 2020/11/12 4:19 下午
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 捕获全局异常,处理所有不可知的异常
    @ExceptionHandler(value = Exception.class)
    Object handleException(Exception e, HttpServletRequest request) {
        LOG.error("url: {}, msg: {}", request.getRequestURL(), e.getMessage());
        e.printStackTrace();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 100);
        map.put("msg", e.getMessage());
        map.put("url", request.getRequestURL());
        return map;
    }

    /**
     * 功能描述：处理自定义异常
     *
     * @return
     */
    @ExceptionHandler(value = MyException.class)
    Object handleMyException(MyException e, HttpServletRequest request) {

        // 返回json数据，由前端去判断加载什么页面
        Map<String, Object> map = new HashMap<>();
        map.put("code", e.getCode());
        map.put("msg", e.getMsg());
        map.put("url", request.getRequestURL());
        return map;
    }

}