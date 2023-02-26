package com.github.wujie0628.app.search.controller;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.github.wujie0628.app.poetry.es.feign.PoetryEsClient;
import com.github.wujie0628.app.poetry.feign.PoetryClient;
import com.github.wujie0628.app.search.config.KafkaInitialConfiguration;
import com.github.wujie0628.common.entity.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class SearchController {

    @Autowired
    PoetryClient poetryClient;
    @Autowired
    PoetryEsClient poetryEsClient;
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @GetMapping("/associative")
    private Result associative(String text) {
        Result r = poetryEsClient.associative(text);
        kafkaTemplate.send(KafkaInitialConfiguration.TOPPIC_NAME, JSONUtil.toJsonStr(r.getData()));
        return r;
//        return poetryClient.associative(text);
    }

    @GetMapping("/getSearchContent")
    private Result getSearchContent(int id) {
        return poetryClient.getSearchContent(id);
    }

}
