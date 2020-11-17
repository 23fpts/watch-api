package com.thc.watchapi.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.thc.watchapi.mapper.WatchDataMapper;
import com.thc.watchapi.model.WatchData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<WatchData> queryDataPeriod(String startTime, String endTime) {
        QueryWrapper<WatchData> wrapper = new QueryWrapper<>();
        wrapper.between("gmt_create", startTime, endTime).orderByAsc("gmt_create");
        List<WatchData> watchDataList = watchDataMapper.selectList(wrapper);
        return watchDataList;
    }

}
