package com.github.wujie0628.app.poetry.rest;


import com.github.wujie0628.app.poetry.service.IPoetryParagraphService;
import com.github.wujie0628.common.entity.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wujie
 * @since 2023-02-01
 */
@RestController
@RequestMapping("/poetry-paragraph")
public class PoetryParagraphController {

    @Autowired
    IPoetryParagraphService poetryParagraphService;

    @GetMapping("/associative")
    private Result associative(String text) {
        return Result.success(poetryParagraphService.getAssociativeText(text));
    }

    @GetMapping("/getSearchContent")
    private Result getSearchContent(int id) {
        return Result.success(poetryParagraphService.getSearchContent(id));
    }

}
