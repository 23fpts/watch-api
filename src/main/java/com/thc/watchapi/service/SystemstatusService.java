package com.thc.watchapi.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.thc.watchapi.mapper.AirbagvalueMapper;
import com.thc.watchapi.mapper.SystemstatusMapper;
import com.thc.watchapi.model.Airbagvalue;
import com.thc.watchapi.model.Systemstatus;
import com.thc.watchapi.model.WatchData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class SystemstatusService {

    @Autowired
    private SystemstatusMapper systemstatusMapper;

    public List<Systemstatus> queryDataPeriod(String time) {
        QueryWrapper<Systemstatus> wrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(time)){
            wrapper.like("DateTime", time).orderByAsc("id");
        }
        List<Systemstatus> systemstatusList = systemstatusMapper.selectList(wrapper);
        return systemstatusList;
    }

}
