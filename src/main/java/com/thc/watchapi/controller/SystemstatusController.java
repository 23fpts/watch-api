package com.thc.watchapi.controller;


import com.thc.watchapi.response.BaseResult;
import com.thc.watchapi.service.SystemstatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("转角转速表")
@RestController
@RequestMapping("/system")
public class SystemstatusController {

    @Autowired
    private SystemstatusService systemstatusService;

    @ApiOperation(value ="查询转角转速")
    @GetMapping("query")
    public BaseResult<Object> queryUnderTime(@RequestParam(value = "time") String time){

//        return R.ok();
        return BaseResult.success( systemstatusService.queryDataPeriod(time));
    }


    @ApiOperation(value = "按时间段查询系统状态")
    @GetMapping("query1")
    public BaseResult<Object> queryDuringTime(@RequestParam(value = "startTime") String startTime,
                                              @RequestParam(value = "endTime") String endTime) {
        return BaseResult.success(systemstatusService.queryDataPeriod2(startTime,endTime));


    }


}
