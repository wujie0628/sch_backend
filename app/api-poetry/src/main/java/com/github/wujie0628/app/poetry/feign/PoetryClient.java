package com.github.wujie0628.app.poetry.feign;


import com.github.wujie0628.common.entity.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "poetry")
public interface PoetryClient {
    @GetMapping("/poetry-paragraph/associative")
    Result associative(@RequestParam(name="text") String text);

    @GetMapping("/poetry-paragraph/getSearchContent")
    Result getSearchContent(@RequestParam(name="id") int id);
}
