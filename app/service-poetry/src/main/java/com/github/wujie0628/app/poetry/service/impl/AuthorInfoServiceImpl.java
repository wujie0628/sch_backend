package com.github.wujie0628.app.poetry.service.impl;

import com.github.wujie0628.app.poetry.entity.po.AuthorInfo;
import com.github.wujie0628.app.poetry.mapper.AuthorInfoMapper;
import com.github.wujie0628.app.poetry.service.IAuthorInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wujie
 * @since 2023-02-01
 */
@Service
public class AuthorInfoServiceImpl extends ServiceImpl<AuthorInfoMapper, AuthorInfo> implements IAuthorInfoService {

}
