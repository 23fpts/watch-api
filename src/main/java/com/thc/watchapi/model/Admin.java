package com.thc.watchapi.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.thc.watchapi.model.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.model
 * @Description:
 * @date 2020/11/12 4:22 下午
 */
@ApiModel("管理员类")
@TableName("t_admin")
@Data
public class Admin extends BaseModel {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "管理员名字", example = "admin")
    private String name;

    @ApiModelProperty(value = "管理员用户名", example = "admin")
    private String username;

    @ApiModelProperty(value = "管理员密码", example = "$2a$10$jJC2tK4q1NbTNjffSSWEheIUHkT8P.qdnUZYqxW5GzyMiVr08fUwe")
    private String password;

}
