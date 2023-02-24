package com.github.wujie0628.app.poetry.entity.vo;

import lombok.Data;

@Data
public class PoetryParagraphVO {

    private Integer id;

    private Integer poetryInfoId;

    private String value;

    private Integer preId;
}
