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
@TableName("airbagvalue")
@ApiModel(value="Airbagvalue对象", description="")
public class Airbagvalue {

    private static final long serialVersionUID=1L;

    @TableField("ID")
    private String id;

    @TableField("DateTime")
    private String DateTime;

    @TableField("AirSource")
    private String AirSource;

    @TableField("AirBag_1")
    private String airbag1;

    @TableField("AirBag_2")
    private String airbag2;

    @TableField("AirBag_3")
    private String airbag3;

    @TableField("AirBag_4")
    private String airbag4;

    @TableField("AirBag_5")
    private String airbag5;

    @TableField("AirBag_6")
    private String airbag6;

    @TableField("AirBag_7")
    private String airbag7;

    @TableField("AirBag_8")
    private String airbag8;

    @TableField("AirBag_9")
    private String airbag9;

    @TableField("AirBag_10")
    private String airbag10;

    @TableField("AirBag_11")
    private String airbag11;

    @TableField("AirBag_12")
    private String airbag12;

    @TableField("OilSource")
    private String OilSource;

    @TableField("SourceA")
    private String SourceA;

    @TableField("SourceB")
    private String SourceB;



}
