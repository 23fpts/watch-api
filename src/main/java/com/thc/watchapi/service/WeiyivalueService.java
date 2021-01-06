package com.thc.watchapi.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.thc.watchapi.mapper.AirbagvalueMapper;
import com.thc.watchapi.mapper.WeiyivalueMapper;
import com.thc.watchapi.model.Airbagvalue;
import com.thc.watchapi.model.WatchData;
import com.thc.watchapi.model.Weiyivalue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class WeiyivalueService {

    @Autowired
    private WeiyivalueMapper weiyivalueMapper;

    public List<Weiyivalue> queryDataPeriod(String time) {
        QueryWrapper<Weiyivalue> wrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(time)){
            wrapper.like("DateTime", time).orderByAsc("id");
        }
        List<Weiyivalue> weiyivalueList = weiyivalueMapper.selectList(wrapper);
        return weiyivalueList;
    }
}
