package com.github.wujie0628.app.poetry.es.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PoetryParagraphVO implements Serializable {


    private int id;

    private int poetryInfoId;

    private String sentence;

    private int preId;


}
