package com.github.wujie0628.organization.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.wujie0628.organization.entity.po.RoleResourceRelation;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色和资源关系表 服务类
 * </p>
 *
 * @author wujie
 * @since 2022-11-05
 */
public interface IRoleResourceRelationService extends IService<RoleResourceRelation> {

    List<RoleResourceRelation> getByRoleIds(Set<String> roleIds);
}
