package com.thc.watchapi.service;

import cn.hutool.core.io.watch.WatchUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.thc.watchapi.dto.WatchDataDto;
import com.thc.watchapi.mapper.WatchDataHexMapper;
import com.thc.watchapi.mapper.WatchDataMapper;
import com.thc.watchapi.model.WatchData;
import com.thc.watchapi.model.WatchDataHex;
import com.thc.watchapi.utils.WatchDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
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

    /**
     * 手表数据分析
     * @param startTime
     * @param endTime
     * @param mac
     * @return
     */
    public WatchDataDto queryDataPeriodAnalyse(String startTime, String endTime, String mac) {
        QueryWrapper<WatchData> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(mac)){
            wrapper.eq("mac", mac);
        }
        if (!StringUtils.isEmpty(startTime)&&!StringUtils.isEmpty(endTime)){
            wrapper.between("hex_create_time", startTime, endTime).orderByAsc("hex_create_time");
        }
        List<WatchData> watchDataList = watchDataMapper.selectList(wrapper);
        WatchDataDto watchDataDto = new WatchDataDto();
        watchDataDto.setRecords(watchDataList);
        // 开始时间
        watchDataDto.setStartTime(watchDataList.get(0).getHexCreateTime());
        // 结束时间
        watchDataDto.setEndTime(watchDataList.get(watchDataList.size()-1).getHexCreateTime());
        // mac
        watchDataDto.setMac(mac);
        // 总秒数
        watchDataDto.setTotalTime((watchDataList.get(watchDataList.size()-1).getHexCreateTime().getTime()-watchDataList.get(0).getHexCreateTime().getTime())/1000);
        // 卡路里
        watchDataDto.setCalorie(watchDataList.get(watchDataList.size()-1).getCalorie().subtract(watchDataList.get(0).getCalorie()));
        // 距离
        watchDataDto.setDistance(watchDataList.get(watchDataList.size()-1).getDistance().subtract(watchDataList.get(0).getDistance()));
        // 平均配速
        watchDataDto.setAverageSpeed(watchDataList.get(watchDataList.size()-1).getAverageSpeed());
        // 平均心率
        Double totalHeartRate = 0.0;
        // 平均步频
        watchDataDto.setAverageCadence(watchDataList.get(watchDataList.size()-1).getAverageCadence());
        // 最大心率
        Integer maxHeartRate = 0;
        // 正常心率区间60-100的百分比(如92%则为92
        Double healthHeartTotal = 0.0;
        for (WatchData watch: watchDataList) {
            if (maxHeartRate<watch.getRealTimeHeartRate()){
                maxHeartRate = watch.getRealTimeHeartRate();
            }
            totalHeartRate += watch.getRealTimeHeartRate();
            if (watch.getRealTimeHeartRate()>=60&&watch.getRealTimeHeartRate()<=100) {
                healthHeartTotal ++;
            }
        }
        watchDataDto.setAverageHeartRate(totalHeartRate/watchDataList.size());
        watchDataDto.setMaxHeartRate(maxHeartRate);
        watchDataDto.setHealthyHeartRate((healthHeartTotal/watchDataList.size()*100));
        // 运动类型处理
        watchDataDto.setSportsType(WatchDataUtil.sportsType2String(watchDataList.get(0).getSportsType()));
        return watchDataDto;
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
