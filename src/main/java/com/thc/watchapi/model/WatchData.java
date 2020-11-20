package com.thc.watchapi.model;

import com.baomidou.mybatisplus.annotation.*;
import com.thc.watchapi.model.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author thc
 * @Title:
 * @Package com.thc.bluetoothtest.model
 * @Description:
 * @date 2020/11/3 2:52 下午
 */
@Data
@TableName("watch_data")
@ToString
@ApiModel("手表数据类")
public class WatchData extends BaseModel {


    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "用户id", example = "1")
    private Integer userId;

    @ApiModelProperty(value = "mac地址", example = "75282AB1D3FD")
    private String mac;

    @ApiModelProperty(value = "运动时长（单位：秒）", example = "3160")
    private BigInteger sportsTime;

    @ApiModelProperty(value = "实时心率", example = "0")
    private BigInteger realTimeHeartRate;

    @ApiModelProperty(value = "平均心率", example = "83")
    private BigInteger averageHeartRate;

    @ApiModelProperty(value = "距离（单位：厘米）", example = "3116")
    private BigInteger distance;

    @ApiModelProperty(value = "卡路里 单位：小卡", example = "1909")
    private BigInteger calorie;

    @ApiModelProperty(value = "总步数", example = "41")
    private BigInteger totalStepCount;

    @ApiModelProperty(value = "实时步频", example = "0")
    private BigInteger realTimeCadence;

    @ApiModelProperty(value = "平均步频", example = "0")
    private BigInteger averageCadence;

    @ApiModelProperty(value = "运动类型", example = "1")
    private Integer sportsType;

    @ApiModelProperty(value = "实时速度", example = "0.000000")
    private BigDecimal realTimeSpeed;

    @ApiModelProperty(value = "平均速度", example = "35876.000000")
    private BigDecimal averageSpeed;

    @ApiModelProperty(value = "运动状态", example = "2")
    private Integer sportsStatus;

    @ApiModelProperty(value = "经度", example = "0.000000")
    private BigDecimal longitude;

    @ApiModelProperty(value = "纬度", example = "0.000000")
    private BigDecimal latitude;

    @ApiModelProperty(value = "16进制数据的创建时间", example = "2020-11-17 16:11:28")
    private Date hexCreateTime;

}
