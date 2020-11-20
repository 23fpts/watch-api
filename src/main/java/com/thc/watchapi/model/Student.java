package com.thc.watchapi.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.thc.watchapi.model.base.BaseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.model
 * @Description: 学员
 *
 * 主要字段：学号、姓名、出生日期、所属学院、大队、学员队、专业、年级、民族、籍贯native place、头像
 * @date 2020/11/13 4:19 下午
 */
@Data
@TableName("t_student")
@ApiModel("学员数据类")
public class Student extends BaseModel {

    @ApiModelProperty(value = "姓名", example = "张三")
    private String name;

    @ApiModelProperty(value = "密码", example = "$2a$10$jJC2tK4q1NbTNjffSSWEheIUHkT8P.qdnUZYqxW5GzyMiVr08fUwe")
    private String password;

    @ApiModelProperty(value = "手表id", example = "1")
    private Integer watchId;

    @ApiModelProperty(value = "手表MAC", example = "24161FDAA3FD")
    private String watchMac;

    @ApiModelProperty(value = "学号", example = "2020123220123")
    private String idNumber;

    @ApiModelProperty(value = "生日", example = "1990-11-19")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;

    @ApiModelProperty(value = "所属学院", example = "计算机学员")
    private String school;

    @ApiModelProperty(value = "大队", example = "跑步队")
    private String team;

    @ApiModelProperty(value = "学员队", example = "跑步1队")
    private String stuTeam;

    @ApiModelProperty(value = "专业", example = "计算机")
    private String major;

    @ApiModelProperty(value = "年级", example = "研一")
    private String grade;

    @ApiModelProperty(value = "民族", example = "汉族")
    private String nation;

    @ApiModelProperty(value = "籍贯", example = "湖北武汉")
    private String nativePlace;

    @ApiModelProperty(value = "头像", example = "https://edu-kex.oss-cn-shanghai.aliyuncs.com/IMG_4265.jpeg")
    private String avatar;

}
