package com.github.wujie0628.app.poetry.service;

import com.github.wujie0628.app.poetry.entity.po.PoetryParagraph;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.wujie0628.app.poetry.entity.vo.PoetryParagraphVO;
import com.github.wujie0628.app.poetry.entity.vo.SearchResult;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wujie
 * @since 2023-02-01
 */
public interface IPoetryParagraphService extends IService<PoetryParagraph> {

    List<PoetryParagraphVO> getAssociativeText(String text);

    SearchResult getSearchContent(int id);
}
