package com.thc.watchapi.dto;

import com.thc.watchapi.model.Admin;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.dto
 * @Description:
 * @date 2020/11/20 10:29 上午
 */
@ApiModel("登录返回数据")
@Data
public class LoginDto {

    private String token;
    private Admin user;

}
