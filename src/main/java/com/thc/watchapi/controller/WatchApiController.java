package com.thc.watchapi.controller;

import com.thc.watchapi.response.R;
import com.thc.watchapi.service.WatchApiService;
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
@RestController
@RequestMapping("/api/v1/web/watchapi")
public class WatchApiController {

    @Autowired
    private WatchApiService watchApiService;

    @GetMapping("connect")
    public R connect() throws Exception {
        watchApiService.connect();
        return R.ok();
    }

    @GetMapping("read")
    public R read() throws Exception {
        watchApiService.read();
        return R.ok();
    }
}
