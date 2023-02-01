package com.github.wujie0628.auth.authorization.service;

import com.github.wujie0628.auth.authorization.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface IRoleService {
    List<Role> queryUserRolesByUserId(String id);
}
