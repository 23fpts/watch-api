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
@TableName("weiyivalue")
@ApiModel(value="Weiyivalue对象", description="")
public class Weiyivalue  {

    private static final long serialVersionUID=1L;

    @TableField("Id")
    private String Id;

    @TableField("DateTime")
    private String DateTime;

    @TableField("WeiYi_1")
    private String weiyi1;

    @TableField("WeiYi_2")
    private String weiyi2;

    @TableField("WeiYi_3")
    private String weiyi3;

    @TableField("WeiYi_4")
    private String weiyi4;

    @TableField("WeiYi_5")
    private String weiyi5;

    @TableField("WeiYi_6")
    private String weiyi6;

    @TableField("WeiYi_7")
    private String weiyi7;

    @TableField("Charge_Total")
    private String chargeTotal;

    @TableField("Charge_Time")
    private String chargeTime;

    @TableField("Charge_AirbagNum")
    private String chargeAirbagnum;

    @TableField("Def_Total")
    private String defTotal;

    @TableField("Def_Time")
    private String defTime;

    @TableField("Def_AirbagNum")
    private String defAirbagnum;



}
