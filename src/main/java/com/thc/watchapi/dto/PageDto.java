package com.thc.watchapi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.dto
 * @Description:
 * @date 2020/11/20 5:22 下午
 */
@Data
@ApiModel("分页")
public class PageDto<T> {

    @ApiModelProperty("该页数据")
    private List<T> records;

    @ApiModelProperty("总数据")
    private Long total;


}
