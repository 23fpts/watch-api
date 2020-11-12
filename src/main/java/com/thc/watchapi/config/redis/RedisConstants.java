package com.thc.watchapi.config.redis;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.utils
 * @Description: 用于区分redis的库
 * @date 2020/11/12 3:34 下午
 */
public class RedisConstants {

    public static final String spilt = ":";

    /**
     * redis库1
     */
    public static final Integer datebase1 = 1;

    /**
     * redis库2 存储登录用户信息
     */
    public static final Integer datebase2 = 2;

    /**
     * redis库3 短信验证码
     */
    public static final Integer datebase3 = 3;

    /**
     * redis库4 扫码登录信息
     *
     */
    public static final Integer datebase4 = 4;

    /**
     * redis库5
     */
    public static final Integer datebase5 = 5;

    /**
     * redis库6
     */
    public static final Integer datebase6 = 6;

    /**
     * redis库7
     */
    public static final Integer datebase7 = 7;

    /**
     * redis库8
     */
    public static final Integer datebase8 = 8;
}