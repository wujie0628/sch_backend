package com.github.wujie0628.app.poetry.es.service;

import com.github.wujie0628.app.poetry.es.entity.po.PoetryParagraph;
import com.github.wujie0628.app.poetry.es.entity.vo.PoetryParagraphVO;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wujie
 * @since 2023-02-01
 */
public interface IPoetryParagraphService  {
    List<PoetryParagraphVO> getAssociativeText(String text) throws IOException;
}
