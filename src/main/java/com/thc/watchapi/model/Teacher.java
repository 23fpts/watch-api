package com.thc.watchapi.model;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import java.io.Serializable;

import com.thc.watchapi.model.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @ProjectName: demo
 * @Package: com.thc.watchapi.model
 * @ClassName: Teacher
 * @Author: Hasee
 * @Description:
 * @Date: 2020/11/20 19:42
 * @Version: 1.0
 */
/**
 * <p>
 * 教员
 * </p>
 *
 * @author thc
 * @since 2020-11-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_teacher")
@ApiModel(value="Teacher对象", description="教员")
public class Teacher extends BaseModel {

    private static final long serialVersionUID=1L;


    @ApiModelProperty(value = "职工id")
    private String workerId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "出生日期")
    private Date birthday;

    @ApiModelProperty(value = "岗位")
    private String job;

    @ApiModelProperty(value = "所属部门")
    private String department;

}
