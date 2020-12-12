package com.thc.watchapi.model.grade;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.model.grade
 * @Description:
 * @date 2020/12/11 9:15 下午
 */
@Data
@TableName("gd_subject")
public class GdSubject implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "课程名字")
    private String name;

    @ApiModelProperty(value = "课程权重")
    private Double weight;
}
