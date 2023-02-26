package com.github.wujie0628.app.poetry.es.controller;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.github.wujie0628.app.poetry.es.service.IPoetryParagraphService;
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

    @Autowired
    IPoetryParagraphService poetryParagraphService;

    @GetMapping("/associative")
    private Result associative(String text) throws IOException {

        return Result.success(poetryParagraphService.getAssociativeText(text));
    }

}
