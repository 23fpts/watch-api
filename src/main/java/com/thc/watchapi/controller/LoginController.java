package com.thc.watchapi.controller;

import com.thc.watchapi.dto.LoginDto;
import com.thc.watchapi.model.Admin;
import com.thc.watchapi.response.BaseResult;
import com.thc.watchapi.response.R;
import com.thc.watchapi.service.LoginService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.controller
 * @Description:
 * @date 2020/11/12 6:05 下午
 */
@Api("后台管理员登录")
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
    public BaseResult<LoginDto> login(@RequestParam String username, @RequestParam String password) {
        System.out.println("controller");
        // return R.ok().data(loginService.login(username, password));
        System.out.println("name: "+username);
        System.out.println("pass: "+password);
        return BaseResult.success(loginService.login(username, password));
    }

    @GetMapping(value = "userinfo")
    public BaseResult<Admin> userInfo(@RequestParam String token) {
        return BaseResult.success(loginService.userInfo(token));
    }


}
