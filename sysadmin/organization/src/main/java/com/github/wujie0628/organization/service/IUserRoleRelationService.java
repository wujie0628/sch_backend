package com.github.wujie0628.organization.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.wujie0628.organization.entity.po.Role;
import com.github.wujie0628.organization.entity.po.UserRoleRelation;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户和角色关系表 服务类
 * </p>
 *
 * @author wujie
 * @since 2022-11-05
 */
public interface IUserRoleRelationService extends IService<UserRoleRelation> {

    Set<String> queryRoleIdsByUserId(String userId);

    List<UserRoleRelation> queryUserRoleRelationByUserId(String userId);
}
