package com.thc.watchapi.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.thc.watchapi.mapper.AirbagvalueMapper;
import com.thc.watchapi.model.Airbagvalue;
import com.thc.watchapi.model.WatchData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class AirbagvalueService {

    @Autowired
    private AirbagvalueMapper airbagvalueMapper;

    public List<Airbagvalue> queryDataPeriod(String time) {
        QueryWrapper<Airbagvalue> wrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(time)){
            wrapper.like("DateTime", time).orderByAsc("ID");
        }
        List<Airbagvalue> AirbagvalueList = airbagvalueMapper.selectList(wrapper);
        return AirbagvalueList;
    }


}
