package com.thc.watchapi.controller;

import com.thc.watchapi.model.WatchData;
import com.thc.watchapi.response.BaseResult;
import com.thc.watchapi.response.R;
import com.thc.watchapi.service.WatchDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.controller
 * @Description:
 * @date 2020/11/17 2:35 下午
 */
@Api(tags = "手表数据处理")
@RestController
@RequestMapping("/api/v1/web/watchdata")
public class WatchDataController {

    @Autowired
    private WatchDataService watchDataService;

    @ApiImplicitParams({
            @ApiImplicitParam(name="startTime",value="查询的起始时间",required=true,paramType="form", defaultValue = "2020-11-17 16:11:28"),
            @ApiImplicitParam(name="endTime",value="查询的结束时间",required=true,paramType="form", defaultValue = "2020-11-17 16:11:37"),
    })
    @ApiOperation("查询一段时间的手表数据")
    @GetMapping("queryDataPeriod")
    public BaseResult<List<WatchData>> queryDataPeriod(@RequestParam(value = "startTime") String startTime,
                                      @RequestParam(value = "endTime") String endTime){
        System.out.println("controller");
        List<WatchData> watchDataList = watchDataService.queryDataPeriod(startTime, endTime);
        // return R.ok().data(watchDataList);
        return BaseResult.success(watchDataList);
    }

    @ApiOperation("16进制数据转换")
    @GetMapping("transferHex2Data")
    public BaseResult<Object> transferHex2Data(@RequestParam(value = "startTime") String startTime,
                             @RequestParam(value = "endTime") String endTime){
        System.out.println("controller");
        watchDataService.transferHex2Data(startTime, endTime);
//        return R.ok();
        return BaseResult.success();
    }


}
