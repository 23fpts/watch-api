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
@TableName("systemstatus")
@ApiModel(value="Systemstatus对象", description="")
public class Systemstatus  {

    private static final long serialVersionUID=1L;

    @TableField("Id")
    private String Id;

    @TableField("DateTime")
    private String DateTime;

    @TableField("SystemStatus")
    private String SystemStatus;

    @TableField("AutoControl")
    private String AutoControl;

    @TableField("Hor_Angle")
    private String horAngle;

    @TableField("Ver_Angle")
    private String verAngle;

    @TableField("X_Offset")
    private String xOffset;

    @TableField("Y_Offset")
    private String yOffset;

    @TableField("X_Deflection")
    private String xDeflection;

    @TableField("Y_Deflection")
    private String yDeflection;

    @TableField("Speed")
    private String Speed;

    @TableField("AlignCenterStatus")
    private String AlignCenterStatus;


}
