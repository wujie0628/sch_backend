package com.github.wujie0628.organization.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wujie0628.organization.entity.po.Role;
import com.github.wujie0628.organization.mapper.RolesMapper;
import com.github.wujie0628.organization.service.IRolesService;
import com.github.wujie0628.organization.service.IUserRoleRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author wujie
 * @since 2022-11-05
 */
@Service
public class RolesServiceImpl extends ServiceImpl<RolesMapper, Role> implements IRolesService {
    @Autowired
    private IUserRoleRelationService userRoleRelationService;
    @Autowired
    private IRolesService rolesService;

    @Override
    public List<Role> getRolesByUserId(String userId) {
        Set<String> roleIds = userRoleRelationService.queryRoleIdsByUserId(userId);
        return rolesService.listByIds(roleIds);
    }
}
