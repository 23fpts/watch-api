package com.thc.watchapi.dto;

import com.thc.watchapi.model.grade2.BctStudentInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.dto
 * @Description:
 * @date 2020/12/30 11:55 上午
 */
@Data
public class Grade2StudentDto extends BctStudentInfo {

    // 分数
    @ApiModelProperty(value = "分数")
    private List<Grade2Dto> scores;

    // 纳入学员
    @ApiModelProperty(value = "纳入学员")
    private Integer studentNumber;

    // 考核课程数
    @ApiModelProperty(value = "考核课程数")
    private Integer subjectNumber;

    // 标准分最大值M
    @ApiModelProperty(value = "M")
    private String max;

    // 标准分最小值m
    @ApiModelProperty(value = "m")
    private String min;

    @ApiModelProperty(value = "总评标准分")
    private Double totalStandardScore;

}
