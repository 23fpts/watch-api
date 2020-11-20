package com.thc.watchapi.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.response
 * @Description: 结果返回类（范性）
 * @date 2020/11/20 9:57 上午
 */
@Data
@ApiModel("通用返回类型")
public class BaseResult<T> implements Serializable {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回信息")
    private String msg;

    @ApiModelProperty(value = "返回数据")
    private T data;

    //几种构造方法
    public BaseResult() {
    }

    public BaseResult(BaseResult.Type type, String msg) {
        this.code = type.value;
        this.msg = msg;
    }

    public BaseResult(BaseResult.Type type, String msg, T data) {
        this.code = type.value;
        this.msg = msg;
        if (data != null) {
            this.data = data;
        }

    }

    //静态方法要使用泛型参数的话，要声明其为泛型方法
    public static <T> BaseResult<T> success() {
        return success("操作成功");
    }

    public static <T> BaseResult<T> success(T data) {
        return success("操作成功", data);
    }

    public static <T> BaseResult<T> success(String msg) {
        return success(msg, (T) null);
    }

    public static <T> BaseResult<T> success(String msg, T data) {
        return new BaseResult<T>(BaseResult.Type.SUCCESS, msg, data);
    }

    public static <T> BaseResult<T> warn(String msg) {
        return warn(msg, (T) null);
    }

    public static <T> BaseResult<T> warn(String msg, T data) {
        return new BaseResult<T>(BaseResult.Type.WARN, msg, data);
    }


    public static <T> BaseResult<T> unAuth() {
        return new BaseResult<T>(Type.UNAUTH, "未登陆", (T) null);
    }

    public static <T> BaseResult<T> error() {
        return error("操作失败");
    }

    public static <T> BaseResult<T> error(String msg) {
        return error(msg, (T) null);
    }

    public static <T> BaseResult<T> error(String msg, T data) {
        return new BaseResult<T>(BaseResult.Type.ERROR, msg, data);
    }

    public static enum Type {
        SUCCESS(200),
        WARN(402),
        UNAUTH(401),
        ERROR(500);

        private final int value;

        private Type(int value) {
            this.value = value;
        }
    }

}