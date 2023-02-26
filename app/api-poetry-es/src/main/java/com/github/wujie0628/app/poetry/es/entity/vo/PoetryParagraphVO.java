package com.github.wujie0628.app.poetry.es.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
public class PoetryParagraphVO implements Serializable {


    private int id;

    private int poetryInfoId;

    private String value;

    private int preId;


}
