package com.thc.watchapi.controller;


import com.thc.watchapi.response.BaseResult;
import com.thc.watchapi.service.AirbagvalueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "压力表查询")
@RestController
@RequestMapping("/airbag")
public class AirbagvalueController {

    @Autowired
    private AirbagvalueService airbagvalueService;

    @ApiOperation(value ="按时间查询压力值")
    @GetMapping("query")
    public BaseResult<Object> queryUnderTime(@RequestParam(value = "time") String time){


//        return R.ok();
        return BaseResult.success(airbagvalueService.queryDataPeriod(time));
    }

    @ApiOperation(value = "按时间段查询压力值")
    @GetMapping("query1")
    public BaseResult<Object> queryDuringTime(@RequestParam(value = "startTime") String startTime,
                                              @RequestParam(value = "endTime") String endTime) {
        return BaseResult.success(airbagvalueService.queryDataPeriod2(startTime,endTime));


    }


}
