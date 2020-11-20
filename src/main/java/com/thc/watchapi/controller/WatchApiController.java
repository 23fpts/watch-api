package com.thc.watchapi.controller;

import com.thc.watchapi.response.BaseResult;
import com.thc.watchapi.response.R;
import com.thc.watchapi.service.WatchApiService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.controller
 * @Description:
 * @date 2020/11/13 5:25 下午
 */
@Api(tags = "网关api调用")
@RestController
@RequestMapping("/api/v1/web/watchapi")
public class WatchApiController {

    @Autowired
    private WatchApiService watchApiService;

    @GetMapping("connect")
    public BaseResult connect() throws Exception {
        watchApiService.connect();
//        return R.ok();
        return BaseResult.success();
    }

    @GetMapping("read")
    public BaseResult<Object> read() throws Exception {
        watchApiService.read();
//        return R.ok();
        return BaseResult.success();
    }

    @GetMapping("close")
    public BaseResult<Object> close() throws Exception {
        watchApiService.close();
//        return R.ok();
        return BaseResult.success();
    }
}
