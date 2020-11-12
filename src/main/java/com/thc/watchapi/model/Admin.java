package com.thc.watchapi.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.thc.watchapi.model.base.BaseModel;
import lombok.Data;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.model
 * @Description:
 * @date 2020/11/12 4:22 下午
 */
@TableName("t_admin")
@Data
public class Admin extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String name;

    private String username;

    private String password;

}
