package com.thc.watchapi.model.grade2;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 学生信息基础表
 * </p>
 *
 * @author thc
 * @since 2020-12-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bct_student_info")
@ApiModel(value="BctStudentInfo对象", description="学生信息基础表")
public class BctStudentInfo implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "ID")
    @TableId("ID_")
    private String stuId;

    @ApiModelProperty(value = "学员学号")
    @TableField("STU_NO_")
    private String stuNoInStu;

    @ApiModelProperty(value = "学员姓名")
    @TableField("STU_NAME_")
    private String stuName;

    @ApiModelProperty(value = "性别")
    @TableField("SEX_")
    private String sex;

    @ApiModelProperty(value = "出生日期")
    @TableField("BIRTHDAY_")
    private Date birthday;

    @ApiModelProperty(value = "民族")
    @TableField("NATIONAL_")
    private String national;

    @ApiModelProperty(value = "籍贯")
    @TableField("PLACE_")
    private String place;

    @ApiModelProperty(value = "专业")
    @TableField("MAJOR_")
    private String major;

    @ApiModelProperty(value = "政治面貌")
    @TableField("PARTY_")
    private String party;

    @ApiModelProperty(value = "通信地址")
    @TableField("ADDRESS_")
    private String address;

    @ApiModelProperty(value = "电子邮件")
    @TableField("EMAIL_")
    private String email;

    @ApiModelProperty(value = "学员队")
    @TableField("TEAM_")
    private String team;

    @ApiModelProperty(value = "创建人")
    @TableField("CREATE_USER_")
    private String stuCreateUser;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME_")
    private Date stuCreateTime;

    @ApiModelProperty(value = "学生照片")
    @TableField("PIC_")
    private String pic;

    @ApiModelProperty(value = "入党团时间")
    @TableField("PARTY_TIME_")
    private Date partyTime;

    @ApiModelProperty(value = "个人电话")
    @TableField("PHONE_")
    private String phone;

    @ApiModelProperty(value = "微信号")
    @TableField("WEIXIN_")
    private String weixin;

    @ApiModelProperty(value = "微博号")
    @TableField("WEBO_")
    private String webo;

    @ApiModelProperty(value = "学院名称")
    @TableField("COLLEGE_")
    private String college;

    @ApiModelProperty(value = "入学日期")
    @TableField("ENROL_DATE_")
    private Date enrolDate;

    @ApiModelProperty(value = "0未校对，1审批中，2已校对完")
    @TableField("IS_CHECK_")
    private String isCheck;

    @ApiModelProperty(value = "流程状态")
    @TableField("PROCESS_ID_")
    private String processId;

    @ApiModelProperty(value = "年级")
    @TableField("GRADE_")
    private String grade;

    @ApiModelProperty(value = "学员状态(在校，离校)")
    @TableField("STU_STATUS_")
    private String stuStatus;

    @ApiModelProperty(value = "身份证")
    @TableField("CARD_")
    private String card;

    @ApiModelProperty(value = "学制(6月30号为离校时间)")
    @TableField("SCHOOL_SYS_")
    private String schoolSys;

    @ApiModelProperty(value = "大队")
    @TableField("BRIGADE_")
    private String brigade;



}
