package com.thc.watchapi.controller;

import com.thc.watchapi.model.WatchData;
import com.thc.watchapi.response.R;
import com.thc.watchapi.service.WatchDataService;
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
@RestController
@RequestMapping("/api/v1/web/watchdata")
public class WatchDataController {

    @Autowired
    private WatchDataService watchDataService;

    @GetMapping("queryDataPeriod")
    public R queryDataPeriod(@RequestParam(value = "startTime") String startTime,
                             @RequestParam(value = "endTime") String endTime){
        System.out.println("controller");
        List<WatchData> watchDataList = watchDataService.queryDataPeriod(startTime, endTime);
        return R.ok().data(watchDataList);
    }

    @GetMapping("transferHex2Data")
    public R transferHex2Data(@RequestParam(value = "startTime") String startTime,
                             @RequestParam(value = "endTime") String endTime){
        System.out.println("controller");
        return R.ok();
    }

}
