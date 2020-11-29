package com.thc.watchapi.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.dto
 * @Description:
 * @date 2020/11/28 8:44 下午
 */
@Data
public class WatchDataQuery {

    private String startTime;

    private String endTime;

    private String mac;
}
