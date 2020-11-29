package com.thc.watchapi.controller;

import com.thc.watchapi.dto.WatchDataQuery;
import com.thc.watchapi.model.WatchData;
import com.thc.watchapi.response.BaseResult;
import com.thc.watchapi.response.R;
import com.thc.watchapi.service.WatchDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    // TODO 将startTime和endTime的require设为false
    // TODO 总时间 距离 
//    @ApiImplicitParams({
//            @ApiImplicitParam(name="startTime",value="查询的起始时间",required=false, paramType="query", defaultValue = "2020-11-17 16:11:28"),
//            @ApiImplicitParam(name="endTime",value="查询的结束时间",required=false, paramType="query", defaultValue = "2020-11-17 16:11:37"),
//            @ApiImplicitParam(name="mac",value="查询的mac, eg:75282AB1D3FD",required=false, paramType="query", defaultValue = "75282AB1D3FD"),
//    })
    @ApiOperation(value = "查询一段时间的手表数据", notes = "查询xxxx-xxxx时间段内所有的手表数据")
    @PostMapping("queryDataPeriod")
    public BaseResult<List<WatchData>> queryDataPeriod(@RequestBody WatchDataQuery watchDataQuery){
        System.out.println("controller");
        List<WatchData> watchDataList = watchDataService.queryDataPeriod(watchDataQuery.getStartTime(), watchDataQuery.getEndTime(), watchDataQuery.getMac());
        // return R.ok().data(watchDataList);
        return BaseResult.success(watchDataList);
    }

    @ApiOperation(value = "16进制数据转换", notes = "将xxxx-xxxx时间段内的所有手表数据从16进制转换为10进制，方便解码")
    @GetMapping("transferHex2Data")
    public BaseResult<Object> transferHex2Data(@RequestParam(value = "startTime") String startTime,
                             @RequestParam(value = "endTime") String endTime){
        System.out.println("controller");
        watchDataService.transferHex2Data(startTime, endTime);
//        return R.ok();
        return BaseResult.success();
    }


}
