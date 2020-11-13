package com.thc.watchapi.model;

import com.baomidou.mybatisplus.annotation.*;
import com.thc.watchapi.model.base.BaseModel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class WatchData extends BaseModel {


    private static final long serialVersionUID=1L;

    private Integer userId;

    private String mac;

    private Integer sportsTime;

    private Integer realTimeHeartRate;

    private Integer averageHeartRate;

    private Integer distance;

    private Integer calorie;

    private Integer totalStepCount;

    private Integer realTimeCadence;

    private Integer averageCadence;

    private Integer sportsType;

    private BigDecimal realTimeSpeed;

    private BigDecimal averageSpeed;

    private Integer sportsStatus;

    private BigDecimal longitude;

    private BigDecimal latitude;

}
