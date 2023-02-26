package com.github.wujie0628.app.poetry.es.service.impl;

import cn.hutool.core.bean.BeanUtil;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.github.wujie0628.app.poetry.entity.po.PoetryInfo;
import com.github.wujie0628.app.poetry.es.entity.po.PoetryParagraph;
import com.github.wujie0628.app.poetry.es.entity.vo.PoetryParagraphVO;
import com.github.wujie0628.app.poetry.es.service.IPoetryParagraphService;
import com.github.wujie0628.app.poetry.feign.PoetryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
public class PoetryParagraphServiceImpl implements IPoetryParagraphService {

    @Autowired
    private PoetryClient poetryClient;
    @Autowired
    ElasticsearchClient client;


    @Override
    public List<PoetryParagraphVO> getAssociativeText(String text) throws IOException {

        SearchResponse<PoetryParagraph> response = client
                .search(s -> s.index("poetry_paragraph")
                        .query(q -> q.match(t -> t.field("sentence").query(text))).from(0).size(20), PoetryParagraph.class);

        List<PoetryParagraph> poetryParagraphList = response.hits().hits().stream().map(t -> t.source()).collect(Collectors.toList());


        List<PoetryParagraphVO> poetryParagraphs = poetryParagraphList.stream().map(p -> {
            PoetryParagraphVO vo = new PoetryParagraphVO();
            BeanUtil.copyProperties(p, vo, true);

            PoetryInfo poetryInfo = poetryClient.getPoetryInfoById(p.getPoetryInfoId());

            vo.setValue(p.getSentence()+ "(" + poetryInfo.getAuthor() + ")");
            return vo;
        }).collect(Collectors.toList());

        return poetryParagraphs;
    }


}
