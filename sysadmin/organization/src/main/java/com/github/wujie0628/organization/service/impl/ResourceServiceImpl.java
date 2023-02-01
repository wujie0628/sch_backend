package com.github.wujie0628.organization.service.impl;

import com.github.wujie0628.organization.entity.po.Resource;
import com.github.wujie0628.organization.entity.po.Role;
import com.github.wujie0628.organization.entity.po.RoleResourceRelation;
import com.github.wujie0628.organization.entity.po.User;
import com.github.wujie0628.organization.mapper.ResourceMapper;
import com.github.wujie0628.organization.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author wujie
 * @since 2022-11-05
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {

    @Autowired
    private IUsersService usersService;
    @Autowired
    private IUserRoleRelationService userRoleRelationService;
    @Autowired
    private IRolesService rolesService;
    @Autowired
    private IResourceService resourceService;
    @Autowired
    private IRoleResourceRelationService roleResourceRelationService;

    @Override
    public Set<Resource> getAll() {
        return this.list().stream().collect(Collectors.toSet());
    }

    @Override
    public Set<Resource> getByUsername(String username) {
        User user = usersService.getByUniqueId(username);
        List<Role> roles = rolesService.getRolesByUserId(user.getId());
        Set<String> roleIds = roles.stream().map(role -> role.getId()).collect(Collectors.toSet());
        List<RoleResourceRelation> roleResourceRelations = roleResourceRelationService.getByRoleIds(roleIds);
        List<String> resourceIds = roleResourceRelations.stream().map(roleResourceRelation -> roleResourceRelation.getResourceId())
                .collect(Collectors.toList());
        return this.listByIds(resourceIds).stream().collect(Collectors.toSet());
    }


}
