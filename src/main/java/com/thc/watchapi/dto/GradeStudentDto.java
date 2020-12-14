package com.thc.watchapi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.model
 * @Description:
 * @date 2020/12/11 8:02 下午
 */
@Data
@ApiModel("学生，一个学生有多个成绩")
@ToString
public class GradeStudentDto {

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "专业")
    private String major;

    @ApiModelProperty(value = "分数")
    private List<GradeDto> score;

    @ApiModelProperty(value = "总评标准分")
    private Double totalStandardScore;
}
