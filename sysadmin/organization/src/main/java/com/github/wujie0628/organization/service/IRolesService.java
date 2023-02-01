package com.github.wujie0628.organization.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.wujie0628.organization.entity.po.Role;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author wujie
 * @since 2022-11-05
 */
public interface IRolesService extends IService<Role> {

    List<Role> getRolesByUserId(String userId);
}
