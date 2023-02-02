package com.github.wujie0628.app.search.rest;


import com.github.wujie0628.app.search.entity.po.PoetryInfo;
import com.github.wujie0628.app.search.service.IPoetryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wujie
 * @since 2023-02-01
 */
@RestController
@RequestMapping("/poetry-info")
public class PoetryInfoController {

    @Autowired
    private IPoetryInfoService poetryInfoService;

    @GetMapping("/test")
    private List<PoetryInfo> test() {
        return poetryInfoService.list();
    }

}
