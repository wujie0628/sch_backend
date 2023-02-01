package com.github.wujie0628.organization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wujie0628.organization.entity.po.RoleResourceRelation;
import com.github.wujie0628.organization.mapper.RoleResourceRelationMapper;
import com.github.wujie0628.organization.service.IRoleResourceRelationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色和资源关系表 服务实现类
 * </p>
 *
 * @author wujie
 * @since 2022-11-05
 */
@Service
public class RoleResourceRelationServiceImpl extends ServiceImpl<RoleResourceRelationMapper, RoleResourceRelation> implements IRoleResourceRelationService {

    @Override
    public List<RoleResourceRelation> getByRoleIds(Set<String> roleIds) {
        LambdaQueryWrapper<RoleResourceRelation> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(RoleResourceRelation::getRoleId, roleIds);
        return this.list(lambdaQueryWrapper);
    }
}
