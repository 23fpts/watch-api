package com.thc.watchapi.dto;

import com.thc.watchapi.model.grade2.BctStudentInfo;
import com.thc.watchapi.model.grade2.BizWListAcademic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.dto
 * @Description:
 * @date 2020/12/30 11:49 上午
 */
@Data
public class Grade2Dto extends BizWListAcademic {

    @ApiModelProperty(value = "平均分")
    private Double averageScore;

    @ApiModelProperty(value = "标准差")
    private Double standardDeviation;

    @ApiModelProperty(value = "单科标准分")
    private Double singleStandardScore;

    @ApiModelProperty(value = "百分制标准分")
    private Double hundredStandardScore;

}
