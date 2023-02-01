package com.github.wujie0628.organization.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.wujie0628.common.entity.po.BasePo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 资源表
 * </p>
 *
 * @author wujie
 * @since 2022-11-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("resource")
@ApiModel(value="Resource对象", description="资源表")
public class Resource extends BasePo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "资源code")
    private String code;

    @ApiModelProperty(value = "资源类型")
    private String type;

    @ApiModelProperty(value = "资源名称")
    private String name;

    @ApiModelProperty(value = "资源url")
    private String url;

    @ApiModelProperty(value = "资源方法")
    private String method;

    @ApiModelProperty(value = "简介")
    private String description;




}
