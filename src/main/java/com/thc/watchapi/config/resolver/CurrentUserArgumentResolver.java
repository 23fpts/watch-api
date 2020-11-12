package com.thc.watchapi.config.resolver;

import com.thc.watchapi.annotation.CurrentUser;
import com.thc.watchapi.model.Admin;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.config.resolver
 * @Description: 使CurrentUser对参数生效
 * @date 2020/11/12 5:13 下午
 */
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // TODO Auto-generated method stub
        return (parameter.getParameterType().isAssignableFrom(Admin.class))
                && parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // TODO Auto-generated method stub
        return webRequest.getAttribute("currentUser", RequestAttributes.SCOPE_REQUEST);
    }

}
