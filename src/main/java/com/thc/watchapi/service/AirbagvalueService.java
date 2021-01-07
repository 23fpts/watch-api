package com.thc.watchapi.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.thc.watchapi.dto.AirbagvalueDTO;
import com.thc.watchapi.mapper.AirbagvalueMapper;
import com.thc.watchapi.model.Airbagvalue;
import com.thc.watchapi.model.WatchData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.DoubleBinaryOperator;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

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


    public AirbagvalueDTO queryDataPeriod2(String startTime, String endTime) {
        QueryWrapper<Airbagvalue> wrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(startTime)&&!StringUtils.isEmpty(endTime)){
            wrapper.between("DateTime", startTime, endTime).orderByAsc("ID");
        }
        List<Airbagvalue> airbagvalueList = airbagvalueMapper.selectList(wrapper);

        Double average1;
        Double average2;
        Double average3;
        Double average4;
        Double average5;
        Double average6;
        Double average7;
        Double average8;
        Double average9;
        Double average10;
        Double average11;
        Double average12;


        Double nums1=0.0;
        Double nums2=0.0;
        Double nums3=0.0;
        Double nums4=0.0;
        Double nums5=0.0;
        Double nums6=0.0;
        Double nums7=0.0;
        Double nums8=0.0;
        Double nums9=0.0;
        Double nums10=0.0;
        Double nums11=0.0;
        Double nums12=0.0;

        List<Double> value1=new ArrayList<>();
        List<Double> value2=new ArrayList<>();
        List<Double> value3=new ArrayList<>();
        List<Double> value4=new ArrayList<>();
        List<Double> value5=new ArrayList<>();
        List<Double> value6=new ArrayList<>();
        List<Double> value7=new ArrayList<>();
        List<Double> value8=new ArrayList<>();
        List<Double> value9=new ArrayList<>();
        List<Double> value10=new ArrayList<>();
        List<Double> value11=new ArrayList<>();
        List<Double> value12=new ArrayList<>();



        for (Airbagvalue airbagvalue : airbagvalueList) {
            nums1=nums1+Double.parseDouble(airbagvalue.getAirbag1());
            nums2=nums2+Double.parseDouble(airbagvalue.getAirbag2());
            nums3=nums3+Double.parseDouble(airbagvalue.getAirbag3());
            nums4=nums4+Double.parseDouble(airbagvalue.getAirbag4());
            nums5=nums5+Double.parseDouble(airbagvalue.getAirbag5());
            nums6=nums6+Double.parseDouble(airbagvalue.getAirbag6());
            nums7=nums7+Double.parseDouble(airbagvalue.getAirbag7());
            nums8=nums8+Double.parseDouble(airbagvalue.getAirbag8());
            nums9=nums9+Double.parseDouble(airbagvalue.getAirbag9());
            nums10=nums10+Double.parseDouble(airbagvalue.getAirbag10());
            nums11=nums11+Double.parseDouble(airbagvalue.getAirbag11());
            nums12=nums12+Double.parseDouble(airbagvalue.getAirbag12());
            value1.add(Double.parseDouble(airbagvalue.getAirbag1()));
            value2.add(Double.parseDouble(airbagvalue.getAirbag2()));
            value3.add(Double.parseDouble(airbagvalue.getAirbag3()));
            value4.add(Double.parseDouble(airbagvalue.getAirbag4()));
            value5.add(Double.parseDouble(airbagvalue.getAirbag5()));
            value6.add(Double.parseDouble(airbagvalue.getAirbag6()));
            value7.add(Double.parseDouble(airbagvalue.getAirbag7()));
            value8.add(Double.parseDouble(airbagvalue.getAirbag8()));
            value9.add(Double.parseDouble(airbagvalue.getAirbag9()));
            value10.add(Double.parseDouble(airbagvalue.getAirbag10()));
            value11.add(Double.parseDouble(airbagvalue.getAirbag11()));
            value12.add(Double.parseDouble(airbagvalue.getAirbag12()));
        }

        //获取峰值
        Double max1 = Collections.max(value1);
        Double max2 = Collections.max(value2);
        Double max3 = Collections.max(value3);
        Double max4 = Collections.max(value4);
        Double max5 = Collections.max(value5);
        Double max6 = Collections.max(value6);
        Double max7 = Collections.max(value7);
        Double max8 = Collections.max(value8);
        Double max9 = Collections.max(value9);
        Double max10 = Collections.max(value10);
        Double max11 = Collections.max(value11);
        Double max12 = Collections.max(value12);

        //获取均值
        average1 = nums1/airbagvalueList.size();
        average2 = nums2/airbagvalueList.size();
        average3 = nums3/airbagvalueList.size();
        average4 = nums4/airbagvalueList.size();
        average5 = nums5/airbagvalueList.size();
        average6 = nums6/airbagvalueList.size();
        average7 = nums7/airbagvalueList.size();
        average8 = nums8/airbagvalueList.size();
        average9 = nums9/airbagvalueList.size();
        average10 = nums10/airbagvalueList.size();
        average11= nums11/airbagvalueList.size();
        average12 = nums12/airbagvalueList.size();
        AirbagvalueDTO dto = new AirbagvalueDTO();
        dto.setList(airbagvalueList);
        dto.setAverage1(average1);
        dto.setAverage2(average2);
        dto.setAverage3(average3);
        dto.setAverage4(average4);
        dto.setAverage5(average5);
        dto.setAverage6(average6);
        dto.setAverage7(average7);
        dto.setAverage8(average8);
        dto.setAverage9(average9);
        dto.setAverage10(average10);
        dto.setAverage11(average11);
        dto.setAverage12(average12);

        dto.setMax1(max1);
        dto.setMax2(max2);
        dto.setMax3(max3);
        dto.setMax4(max4);
        dto.setMax5(max5);
        dto.setMax6(max6);
        dto.setMax7(max7);
        dto.setMax8(max8);
        dto.setMax9(max9);
        dto.setMax10(max10);
        dto.setMax11(max11);
        dto.setMax12(max12);

        return dto;
    }

}
