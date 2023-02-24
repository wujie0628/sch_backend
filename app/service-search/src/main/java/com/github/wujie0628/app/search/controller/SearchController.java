package com.github.wujie0628.app.search.controller;

import com.github.wujie0628.app.poetry.feign.PoetryClient;
import com.github.wujie0628.common.entity.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class SearchController {

    @Autowired
    PoetryClient poetryClient;

    @GetMapping("/associative")
    private Result associative(String text) {
        return poetryClient.associative(text);
    }

    @GetMapping("/getSearchContent")
    private Result getSearchContent(int id) {
        return poetryClient.getSearchContent(id);
    }

}
