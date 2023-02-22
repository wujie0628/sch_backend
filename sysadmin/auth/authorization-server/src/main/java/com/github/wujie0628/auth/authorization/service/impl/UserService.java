package com.github.wujie0628.auth.authorization.service.impl;

import com.github.wujie0628.auth.authorization.entity.User;
import com.github.wujie0628.auth.authorization.provider.OrganizationProvider;
import com.github.wujie0628.auth.authorization.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    OrganizationProvider organizationProvider;

    @Override
    public User getByUniqueId(String uniqueId) {
        return organizationProvider.getByUniqueId(uniqueId).getData();
    }
}
