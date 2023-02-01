package com.github.wujie0628.organization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wujie0628.organization.entity.po.User;
import com.github.wujie0628.organization.mapper.UsersMapper;
import com.github.wujie0628.organization.service.IUsersService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author wujie
 * @since 2022-11-05
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, User> implements IUsersService {

    private int a = 1;

    @Override
    public User getByUniqueId(String uniqueId) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(User::getUsername, uniqueId).or().eq(User::getMobile, uniqueId);
        a++;
        System.out.println(a);
        return this.getOne(queryWrapper);

    }
}
