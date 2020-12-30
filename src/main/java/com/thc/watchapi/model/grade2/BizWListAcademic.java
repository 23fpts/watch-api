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
 * 学业成绩预警名单表
 * </p>
 *
 * @author thc
 * @since 2020-12-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("biz_w_list_academic")
@ApiModel(value="BizWListAcademic对象", description="学业成绩预警名单表")
public class BizWListAcademic implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键ID")
    @TableId("ID_")
    private String acaId;

    @ApiModelProperty(value = "创建人")
    @TableField("CREATE_USER_")
    private String acaCreateUser;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME_")
    private Date acaCreateTime;

    @ApiModelProperty(value = "学号")
    @TableField("STU_NO_")
    private String stuNoInAca;

    @ApiModelProperty(value = "课程")
    @TableField("PROJECT_")
    private String project;

    @ApiModelProperty(value = "成绩")
    @TableField("SCORE_")
    private Double score;

    @ApiModelProperty(value = "考试时间")
    @TableField("EXAM_TIME_")
    private Date examTime;

    @ApiModelProperty(value = "学分")
    @TableField("XF_")
    private Double xf;


}
