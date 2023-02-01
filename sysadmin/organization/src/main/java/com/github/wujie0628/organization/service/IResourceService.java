package com.github.wujie0628.organization.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.wujie0628.organization.entity.po.Resource;

import java.util.Set;

/**
 * <p>
 * 资源表 服务类
 * </p>
 *
 * @author wujie
 * @since 2022-11-05
 */
public interface IResourceService extends IService<Resource> {

    Set<Resource> getAll();

    Set<Resource> getByUsername(String username);
}
