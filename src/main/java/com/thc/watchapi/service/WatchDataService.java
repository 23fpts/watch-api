package com.thc.watchapi.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.thc.watchapi.mapper.WatchDataHexMapper;
import com.thc.watchapi.mapper.WatchDataMapper;
import com.thc.watchapi.model.WatchData;
import com.thc.watchapi.model.WatchDataHex;
import com.thc.watchapi.utils.WatchDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.service
 * @Description:
 * @date 2020/11/17 12:43 下午
 */
@Service
public class WatchDataService {

    @Autowired
    private WatchDataMapper watchDataMapper;

    @Autowired
    private WatchDataHexMapper watchDataHexMapper;

    public List<WatchData> queryDataPeriod(String startTime, String endTime, String mac) {
        QueryWrapper<WatchData> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(mac)){
            wrapper.eq("mac", mac);
        }
        if (!StringUtils.isEmpty(startTime)&&!StringUtils.isEmpty(endTime)){
            wrapper.between("hex_create_time", startTime, endTime).orderByAsc("hex_create_time");
        }
        List<WatchData> watchDataList = watchDataMapper.selectList(wrapper);
        return watchDataList;
    }

    public void transferHex2Data(String startTime, String endTime) {
        QueryWrapper<WatchDataHex> wrapper = new QueryWrapper<>();
        wrapper.between("gmt_create", startTime, endTime);
        List<WatchDataHex> watchDataHexList = watchDataHexMapper.selectList(wrapper);
        for (WatchDataHex watchDataHex: watchDataHexList) {
            WatchData watchData = WatchDataUtil.HexDataToData(watchDataHex.getData());
            watchData.setHexCreateTime(watchDataHex.getGmtCreate());
            watchDataMapper.insert(watchData);
        }
    }

    public void testTrans() {
        WatchDataHex watchDataHex = watchDataHexMapper.selectById(1292);
        WatchData watchData = WatchDataUtil.HexDataToData(watchDataHex.getData());
        watchData.setHexCreateTime(watchDataHex.getGmtCreate());
        watchDataMapper.insert(watchData);
    }
}
