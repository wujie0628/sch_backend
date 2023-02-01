package com.github.wujie0628.organization.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wujie0628.organization.entity.po.UserGroupRelation;
import com.github.wujie0628.organization.mapper.UserGroupRelationMapper;
import com.github.wujie0628.organization.service.IUserGroupRelationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户和组关系表 服务实现类
 * </p>
 *
 * @author wujie
 * @since 2022-11-05
 */
@Service
public class UserGroupRelationServiceImpl extends ServiceImpl<UserGroupRelationMapper, UserGroupRelation> implements IUserGroupRelationService {

}
