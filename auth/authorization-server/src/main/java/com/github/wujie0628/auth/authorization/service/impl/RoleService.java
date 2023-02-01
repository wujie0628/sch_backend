package com.github.wujie0628.auth.authorization.service.impl;

import com.github.wujie0628.auth.authorization.entity.Role;
import com.github.wujie0628.auth.authorization.provider.OrganizationProvider;
import com.github.wujie0628.auth.authorization.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private OrganizationProvider organizationProvider;

    @Override
    public List<Role> queryUserRolesByUserId(String id) {
        return organizationProvider.getRolesByUserId(id).getData();
    }
}
