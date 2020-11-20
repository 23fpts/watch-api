package com.thc.watchapi.model;

import com.baomidou.mybatisplus.annotation.*;
import com.thc.watchapi.model.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author thc
 * @Title:
 * @Package com.thc.bluetoothtest.model
 * @Description:
 * @date 2020/11/1 2:18 下午
 */
@Data
@TableName("watch_data_hex")
@ToString
@ApiModel("16进制手表数据")
public class WatchDataHex extends BaseModel {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "数据", example = "A5,A5,2C,FD,D3,B1,2A,28,75,59,0C,00,00,00,53,2C,0C,00,00,75,07,00,00,29,00,00,00,00,00,00,00,A7,00,00,44,8C,02,00,00,00,00,00,00,00,00,0D,0A")
    private String data;


}
