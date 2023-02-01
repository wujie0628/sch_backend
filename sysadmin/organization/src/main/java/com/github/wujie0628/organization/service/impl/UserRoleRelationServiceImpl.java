package com.github.wujie0628.organization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wujie0628.organization.entity.po.Role;
import com.github.wujie0628.organization.entity.po.UserRoleRelation;
import com.github.wujie0628.organization.mapper.UserRoleRelationMapper;
import com.github.wujie0628.organization.service.IUserRoleRelationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户和角色关系表 服务实现类
 * </p>
 *
 * @author wujie
 * @since 2022-11-05
 */
@Service
public class UserRoleRelationServiceImpl extends ServiceImpl<UserRoleRelationMapper, UserRoleRelation> implements IUserRoleRelationService {

    @Override
    public List<UserRoleRelation> queryUserRoleRelationByUserId(String userId) {
        LambdaQueryWrapper<UserRoleRelation> lambdaQueryWrapper = new LambdaQueryWrapper<UserRoleRelation>();
        lambdaQueryWrapper.eq(UserRoleRelation::getUserId, userId);
        return this.list(lambdaQueryWrapper);
    }

    @Override
    public Set<String> queryRoleIdsByUserId(String userId) {
        List<UserRoleRelation> userRoleRelations = this.queryUserRoleRelationByUserId(userId);
        return userRoleRelations.stream().map(UserRoleRelation::getRoleId).collect(Collectors.toSet());
    }
}
