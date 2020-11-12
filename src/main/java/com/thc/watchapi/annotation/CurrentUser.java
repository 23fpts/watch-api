package com.thc.watchapi.annotation;

import java.lang.annotation.*;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.annotation
 * @Description: 获得当前用户
 * @date 2020/11/12 4:46 下午
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {

}

