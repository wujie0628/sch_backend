package com.github.wujie0628.organization.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import com.github.wujie0628.common.entity.po.BasePo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author wujie
 * @since 2022-11-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("users")
@ApiModel(value="Users对象", description="用户表")
public class User extends BasePo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户密码密文")
    private String password;

    @ApiModelProperty(value = "用户姓名")
    private String name;

    @ApiModelProperty(value = "用户手机")
    private String mobile;

    @ApiModelProperty(value = "简介")
    private String description;

    @ApiModelProperty(value = "是否已删除Y：已删除，N：未删除")
    private String deleted;

    @ApiModelProperty(value = "是否有效用户")
    private Boolean enabled;

    @ApiModelProperty(value = "账号是否未过期")
    private Boolean accountNonExpired;

    @ApiModelProperty(value = "密码是否未过期")
    private Boolean credentialsNonExpired;

    @ApiModelProperty(value = "是否未锁定")
    private Boolean accountNonLocked;

}
