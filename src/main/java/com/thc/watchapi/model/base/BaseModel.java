package com.thc.watchapi.model.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.model.base
 * @Description:
 * @date 2020/11/12 4:23 下午
 */
@Data
@ToString
public class BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", example = "2019-12-01 10:10:10")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

    /**
     * 删除标志
     */
    @ApiModelProperty(value = "删除标志(true表示删除)", example = "false")
    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    private Boolean deleted;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", example = "2019-12-01 10:10:10")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建者", example = "bluetooth")
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private String createUser;
}
