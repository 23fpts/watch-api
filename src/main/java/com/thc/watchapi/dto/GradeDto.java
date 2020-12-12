package com.thc.watchapi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.model
 * @Description:
 * @date 2020/12/11 7:57 下午
 */
// 用于存放每条成绩的完整信息
@ApiModel("单科分数，一个学生有多个分数")
@Data
@ToString
public class GradeDto {

    @ApiModelProperty(value = "成绩id")
    private Integer gradeId;

    @ApiModelProperty(value = "学生id")
    private Integer studentId;

    @ApiModelProperty(value = "科目id")
    private Integer subjectId;

    @ApiModelProperty(value = "成绩")
    private Double grade;

    @ApiModelProperty(value = "学生姓名")
    private String studentName;

    @ApiModelProperty(value = "专业")
    private String major;

    @ApiModelProperty(value = "课程权重")
    private Double weight;

    @ApiModelProperty(value = "课程名字")
    private String subjectName;

    @ApiModelProperty(value = "平均分")
    private Double averageScore;

    @ApiModelProperty(value = "标准差")
    private Double standardDeviation;

    @ApiModelProperty(value = "单科标准分")
    private Double singleStandardScore;

    @ApiModelProperty(value = "百分制标准分")
    private Double hundredStandardScore;
}
