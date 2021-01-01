package com.thc.watchapi.dto;

import lombok.Data;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.dto
 * @Description:
 * @date 2020/12/30 1:23 下午
 */
@Data
public class GradeAcaQuery {

    private String startTime;

    private String endTime;

    private String grade;

    private String college;

    private String stuNo;

}
