package com.thc.watchapi.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author thc
 * @since 2021-01-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("errorstatus")
@ApiModel(value="Errorstatus对象", description="")
public class Errorstatus  {

    private static final long serialVersionUID=1L;

    @TableField("Id")
    private String Id;

    @TableField("DateTime")
    private String DateTime;

    @TableField("Airbag1_ErrorNum")
    private String airbag1Errornum;

    @TableField("Airbag2_ErrorNum")
    private String airbag2Errornum;

    @TableField("Airbag3_ErrorNum")
    private String airbag3Errornum;

    @TableField("Airbag4_ErrorNum")
    private String airbag4Errornum;

    @TableField("Airbag5_ErrorNum")
    private String airbag5Errornum;

    @TableField("Airbag6_ErrorNum")
    private String airbag6Errornum;

    @TableField("Airbag7_ErrorNum")
    private String airbag7Errornum;

    @TableField("Airbag8_ErrorNum")
    private String airbag8Errornum;

    @TableField("Airbag9_ErrorNum")
    private String airbag9Errornum;

    @TableField("Airbag10_ErrorNum")
    private String airbag10Errornum;

    @TableField("Airbag11_ErrorNum")
    private String airbag11Errornum;

    @TableField("Airbag12_ErrorNum")
    private String airbag12Errornum;

    @TableField("Airsource_ErrorNum")
    private String airsourceErrornum;

    @TableField("Oil_ErrorNum")
    private String oilErrornum;

    @TableField("Angle_ErrorNum")
    private String angleErrornum;

    @TableField("Tempe_ErrorNum")
    private String tempeErrornum;

    @TableField("WeiYi1_ErrorNum")
    private String weiyi1Errornum;

    @TableField("WeiYi2_ErrorNum")
    private String weiyi2Errornum;

    @TableField("WeiYi3_ErrorNum")
    private String weiyi3Errornum;

    @TableField("WeiYi4_ErrorNum")
    private String weiyi4Errornum;

    @TableField("WeiYi5_ErrorNum")
    private String weiyi5Errornum;

    @TableField("WeiYi6_ErrorNum")
    private String weiyi6Errornum;

    @TableField("WeiYi7_ErrorNum")
    private String weiyi7Errornum;

    @TableField("NULL_ErrorNum")
    private String nullErrornum;

    @TableField("Work_ErrorNum")
    private String workErrornum;

    @TableField("Comm_ErrorNum")
    private String commErrornum;

    @TableField("Hor_Align_ErrorNum")
    private String horAlignErrornum;

    @TableField("Ver_Align_ErrorNum")
    private String verAlignErrornum;



}
