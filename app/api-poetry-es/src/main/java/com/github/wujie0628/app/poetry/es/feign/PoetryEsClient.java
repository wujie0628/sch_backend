package com.github.wujie0628.app.poetry.es.feign;


import com.github.wujie0628.common.entity.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "poetry-es")
public interface PoetryEsClient {
    @GetMapping("/poetry-paragraph/associative")
    Result associative(@RequestParam(name="text") String text);

}
