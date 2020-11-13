package com.thc.watchapi.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.thc.watchapi.model.base.BaseModel;
import lombok.Data;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.model
 * @Description: 学员
 * @date 2020/11/13 4:19 下午
 */
@Data
@TableName("t_student")
public class Student extends BaseModel {
    private String name;

    private String password;

    private Integer watchId;

    private String watchMac;


}
