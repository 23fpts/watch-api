package com.thc.watchapi.dto;

import com.thc.watchapi.model.WatchData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.dto
 * @Description:
 * @date 2020/11/29 12:38 下午
 */
@Data
@ApiModel("手表数据")
public class WatchDataDto {

    @ApiModelProperty("手表数据")
    private List<WatchData> records;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;

    @ApiModelProperty("mac")
    private String mac;

    @ApiModelProperty("总秒数")
    private Long totalTime;

    @ApiModelProperty("距离")
    private BigInteger distance;

    @ApiModelProperty("平均配速")
    private BigDecimal averageSpeed;

    @ApiModelProperty("平均心率")
    private Double averageHeartRate;

    @ApiModelProperty("平均步频")
    private BigInteger averageCadence;

    @ApiModelProperty("最大心率")
    private Integer maxHeartRate;

    @ApiModelProperty("卡路里")
    private BigInteger calorie;

    @ApiModelProperty("正常心率区间60-100的百分比(如92%则为92")
    private Double healthyHeartRate;

    @ApiModelProperty("运动类型")
    private String sportsType;
}
