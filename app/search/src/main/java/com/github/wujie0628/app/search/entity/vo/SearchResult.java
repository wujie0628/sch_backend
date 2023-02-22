package com.github.wujie0628.app.search.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SearchResult {

    private Integer id;

    private String title;

    private String author;

    private String dynasty;

    private String content;

    private String twoSentences;

    private String allSentences;
}
