package com.thc.watchapi.controller;

import com.thc.watchapi.response.R;
import com.thc.watchapi.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.controller
 * @Description:
 * @date 2020/11/12 6:05 下午
 */
@RestController
@RequestMapping("/api/v1/web/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 功能描述：登录
     *
     * @param
     * @return
     */
    @PostMapping(value = "login")
    public Object login(@RequestParam String username, @RequestParam String password) {
        System.out.println("controller");
        return R.ok().data(loginService.login(username, password));
    }
}
