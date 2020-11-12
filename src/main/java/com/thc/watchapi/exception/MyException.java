package com.thc.watchapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.exception
 * @Description:
 * @date 2020/11/12 4:06 下午
 */
@Data
// 有参数构造方法
@AllArgsConstructor
// 无参数构造函数
@NoArgsConstructor
public class MyException extends RuntimeException {


    /**
     * 状态吗
     */
    private Integer code;

    /**
     * 异常信息
     */
    private String msg;


}