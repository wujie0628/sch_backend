package com.github.wujie0628.organization.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.wujie0628.organization.entity.po.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author wujie
 * @since 2022-11-05
 */
public interface IUsersService extends IService<User> {

    User getByUniqueId(String uniqueId);
}
