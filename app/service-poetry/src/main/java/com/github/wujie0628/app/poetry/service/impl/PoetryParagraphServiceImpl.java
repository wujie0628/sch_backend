package com.github.wujie0628.app.poetry.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.wujie0628.app.poetry.entity.po.PoetryInfo;
import com.github.wujie0628.app.poetry.entity.po.PoetryParagraph;
import com.github.wujie0628.app.poetry.entity.vo.PoetryParagraphVO;
import com.github.wujie0628.app.poetry.entity.vo.SearchResult;
import com.github.wujie0628.app.poetry.mapper.PoetryParagraphMapper;
import com.github.wujie0628.app.poetry.service.IPoetryInfoService;
import com.github.wujie0628.app.poetry.service.IPoetryParagraphService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wujie0628.common.entity.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wujie
 * @since 2023-02-01
 */
@Service
public class PoetryParagraphServiceImpl extends ServiceImpl<PoetryParagraphMapper, PoetryParagraph> implements IPoetryParagraphService {

    @Autowired
    private PoetryParagraphMapper poetryParagraphMapper;
    @Autowired
    private IPoetryInfoService poetryInfoService;

    @Override
    public List<PoetryParagraphVO> getAssociativeText(String text) {

        LambdaQueryWrapper<PoetryParagraph> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.likeRight(PoetryParagraph::getSentence, text).last(" limit 20");;
        List<PoetryParagraph> poetryParagraphs = poetryParagraphMapper.selectList(lambdaQueryWrapper);

        List<PoetryParagraphVO> poetryParagraphVOS = poetryParagraphs.stream().map(p -> {
            PoetryParagraphVO vo = new PoetryParagraphVO();
            BeanUtil.copyProperties(p, vo, true);

            PoetryInfo poetryInfo = poetryInfoService.getById(p.getPoetryInfoId());

            vo.setValue(p.getSentence() + "(" + poetryInfo.getAuthor() + ")");
            return vo;
        }).collect(Collectors.toList());

        return poetryParagraphVOS;
    }

    @Override
    public SearchResult getSearchContent(int id) {

        SearchResult searchResult = new SearchResult();

        String twoSentences = "";
        String allSentences = "";
        PoetryParagraph previous = this.getById(id);

        LambdaQueryWrapper<PoetryParagraph> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(PoetryParagraph::getPreId, id);
        PoetryParagraph next = this.getOne(lambdaQueryWrapper);

        twoSentences = previous.getSentence() + "-" + next.getSentence();

        PoetryInfo poetryInfo = poetryInfoService.getById(previous.getPoetryInfoId());
        BeanUtil.copyProperties(poetryInfo, searchResult, true);
        searchResult.setTwoSentences(twoSentences);

        String prefix = "<font color=\"red\">";
        String suffix = "</font>";

        allSentences = StringUtil.ReplacementInfo(new StringBuilder(poetryInfo.getContent()), previous.getSentence(), prefix, suffix);

        searchResult.setAllSentences(allSentences);

        return searchResult;
    }
}
