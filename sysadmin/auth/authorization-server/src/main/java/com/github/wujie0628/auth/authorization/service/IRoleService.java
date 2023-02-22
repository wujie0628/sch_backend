package com.github.wujie0628.auth.authorization.service;

import com.github.wujie0628.auth.authorization.entity.Role;

import java.util.List;

public interface IRoleService {
    List<Role> queryUserRolesByUserId(String id);
}
