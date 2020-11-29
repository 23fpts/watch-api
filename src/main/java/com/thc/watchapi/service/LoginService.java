package com.thc.watchapi.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.thc.watchapi.config.redis.RedisConstants;
import com.thc.watchapi.dto.LoginDto;
import com.thc.watchapi.exception.MyException;
import com.thc.watchapi.mapper.AdminMapper;
import com.thc.watchapi.model.Admin;
import com.thc.watchapi.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.service
 * @Description:
 * @date 2020/11/12 5:45 下午
 */
@Service
public class LoginService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${spring.redis.expire}")
    private Long expire;

    /**
     * 123456
     * $2a$10$jJC2tK4q1NbTNjffSSWEheIUHkT8P.qdnUZYqxW5GzyMiVr08fUwe
     */
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    private static String msg = "用户名或密码错误";

    public LoginDto login(String username, String password) {
        System.out.println("service start");
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        Admin admin = adminMapper.selectOne(queryWrapper);
        if (admin == null || !bCryptPasswordEncoder.matches(password, admin.getPassword())) {
            throw new MyException(1, msg);
        }
        String token = UUID.randomUUID().toString().replace("-", "");
        redisUtil.set(token, admin, RedisConstants.datebase2, expire);
        LoginDto loginDto = new LoginDto();
        loginDto.setToken(token);
        loginDto.setUser(admin);
        System.out.println(loginDto);
        return loginDto;
    }

    public Admin userInfo(String token) {
        Admin admin = (Admin)redisUtil.get(token, RedisConstants.datebase2);
        return admin;
    }

}

