package com.github.wujie0628.auth.authorization.service;


import com.github.wujie0628.auth.authorization.entity.User;

public interface IUserService {
    User getByUniqueId(String uniqueId);
}
