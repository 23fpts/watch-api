package com.thc.watchapi.controller;


import com.thc.watchapi.response.BaseResult;
import com.thc.watchapi.service.WeiyivalueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("位移表")
@RestController
@RequestMapping("Weiyi")
public class WeiyivalueController {


    @Autowired
    private WeiyivalueService weiyivalueService;

    @ApiOperation(value ="查询位移数据")
    @GetMapping("query")
    public BaseResult<Object> queryUnderTime(@RequestParam(value = "time") String time){

//        return R.ok();
        return BaseResult.success(weiyivalueService.queryDataPeriod(time));
    }

}
