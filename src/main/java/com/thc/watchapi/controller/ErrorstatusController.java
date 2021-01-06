package com.thc.watchapi.controller;


import com.thc.watchapi.response.BaseResult;
import com.thc.watchapi.service.ErrorstatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("异常状态表")
@RestController
@RequestMapping("/error")
public class ErrorstatusController {

    @Autowired
    private ErrorstatusService errorstatusService;

    @ApiOperation(value ="查询异常数据")
    @GetMapping("query")
    public BaseResult<Object> queryUnderTime(@RequestParam(value = "time") String time){

//        return R.ok();
        return BaseResult.success(errorstatusService.queryDataPeriod(time));
    }

}
