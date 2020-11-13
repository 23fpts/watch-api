package com.thc.watchapi.model;

import com.baomidou.mybatisplus.annotation.*;
import com.thc.watchapi.model.base.BaseModel;
import lombok.Data;

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
public class WatchDataHex extends BaseModel {

    private static final long serialVersionUID=1L;


    private String data;


}
