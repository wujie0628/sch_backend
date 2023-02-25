package com.github.wujie0628.app.poetry.es.controller;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.github.wujie0628.app.poetry.es.entity.vo.PoetryParagraphVO;
import com.github.wujie0628.common.entity.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/poetry-paragraph")
public class PoetryParagraphController {

    @Autowired
    ElasticsearchClient client;

    @GetMapping("/associative")
    private Result associative(String text) throws IOException {

        GetResponse<PoetryParagraphVO> response = client.get(e -> e.index("poetry_paragraph").id("1001"), PoetryParagraphVO.class);

        return Result.success(response.source());
    }

}
