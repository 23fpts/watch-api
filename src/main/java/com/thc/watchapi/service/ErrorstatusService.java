package com.thc.watchapi.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.thc.watchapi.mapper.AirbagvalueMapper;
import com.thc.watchapi.mapper.ErrorstatusMapper;
import com.thc.watchapi.model.Airbagvalue;
import com.thc.watchapi.model.Errorstatus;
import com.thc.watchapi.model.WatchData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ErrorstatusService {

    @Autowired
    private ErrorstatusMapper errorstatusMapper;

    public List<Errorstatus> queryDataPeriod(String time) {
        QueryWrapper<Errorstatus> wrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(time)){
            wrapper.like("DateTime", time).orderByAsc("id");
        }
        List<Errorstatus> errorstatusList = errorstatusMapper.selectList(wrapper);
        return errorstatusList;
    }


}
