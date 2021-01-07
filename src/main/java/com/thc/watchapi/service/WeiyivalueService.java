package com.thc.watchapi.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.thc.watchapi.dto.WeiyiDTO;

import com.thc.watchapi.mapper.WeiyivalueMapper;

import com.thc.watchapi.model.Weiyivalue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
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

    public WeiyiDTO queryDataPeriod2(String startTime, String endTime) {
        QueryWrapper<Weiyivalue> wrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(startTime)&&!StringUtils.isEmpty(endTime)){
            wrapper.between("DateTime", startTime, endTime).orderByAsc("ID");
        }
        List<Weiyivalue> weiyivalueList = weiyivalueMapper.selectList(wrapper);

        Double average1;
        Double average2;
        Double average3;
        Double average4;
        Double average5;
        Double average6;
        Double average7;



        Double nums1=0.0;
        Double nums2=0.0;
        Double nums3=0.0;
        Double nums4=0.0;
        Double nums5=0.0;
        Double nums6=0.0;
        Double nums7=0.0;

        List<Double> value1=new ArrayList<>();
        List<Double> value2=new ArrayList<>();
        List<Double> value3=new ArrayList<>();
        List<Double> value4=new ArrayList<>();
        List<Double> value5=new ArrayList<>();
        List<Double> value6=new ArrayList<>();
        List<Double> value7=new ArrayList<>();



        for (Weiyivalue weiyivalue : weiyivalueList) {
            nums1=nums1+Double.parseDouble(weiyivalue.getWeiyi1());
            nums2=nums2+Double.parseDouble(weiyivalue.getWeiyi2());
            nums3=nums3+Double.parseDouble(weiyivalue.getWeiyi3());
            nums4=nums4+Double.parseDouble(weiyivalue.getWeiyi4());
            nums5=nums5+Double.parseDouble(weiyivalue.getWeiyi5());
            nums6=nums6+Double.parseDouble(weiyivalue.getWeiyi6());
            nums7=nums7+Double.parseDouble(weiyivalue.getWeiyi7());

            value1.add(Double.parseDouble(weiyivalue.getWeiyi1()));
            value2.add(Double.parseDouble(weiyivalue.getWeiyi2()));
            value3.add(Double.parseDouble(weiyivalue.getWeiyi3()));
            value4.add(Double.parseDouble(weiyivalue.getWeiyi4()));
            value5.add(Double.parseDouble(weiyivalue.getWeiyi5()));
            value6.add(Double.parseDouble(weiyivalue.getWeiyi6()));
            value7.add(Double.parseDouble(weiyivalue.getWeiyi7()));
        }

        //获取峰值
        Double max1 = Collections.max(value1);
        Double max2 = Collections.max(value2);
        Double max3 = Collections.max(value3);
        Double max4 = Collections.max(value4);
        Double max5 = Collections.max(value5);
        Double max6 = Collections.max(value6);
        Double max7 = Collections.max(value7);

        //获取均值
        average1 = nums1/weiyivalueList.size();
        average2 = nums2/weiyivalueList.size();
        average3 = nums3/weiyivalueList.size();
        average4 = nums4/weiyivalueList.size();
        average5 = nums5/weiyivalueList.size();
        average6 = nums6/weiyivalueList.size();
        average7 = nums7/weiyivalueList.size();

        WeiyiDTO dto = new WeiyiDTO();
        dto.setList(weiyivalueList);
        dto.setAverage1(average1);
        dto.setAverage2(average2);
        dto.setAverage3(average3);
        dto.setAverage4(average4);
        dto.setAverage5(average5);
        dto.setAverage6(average6);
        dto.setAverage7(average7);

        dto.setMax1(max1);
        dto.setMax2(max2);
        dto.setMax3(max3);
        dto.setMax4(max4);
        dto.setMax5(max5);
        dto.setMax6(max6);
        dto.setMax7(max7);


        return dto;
    }


}
