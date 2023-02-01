package com.github.wujie0628.organization.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wujie0628.organization.entity.po.TGroups;
import com.github.wujie0628.organization.mapper.TGroupsMapper;
import com.github.wujie0628.organization.service.ITGroupsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户组表 服务实现类
 * </p>
 *
 * @author wujie
 * @since 2022-11-05
 */
@Service
public class TGroupsServiceImpl extends ServiceImpl<TGroupsMapper, TGroups> implements ITGroupsService {

}
