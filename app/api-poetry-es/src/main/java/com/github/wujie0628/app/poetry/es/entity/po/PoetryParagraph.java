package com.github.wujie0628.app.poetry.es.entity.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PoetryParagraph implements Serializable {


    private int id;

    @JsonProperty("poetry_info_id")
    private int poetryInfoId;

    private String sentence;

    @JsonProperty("pre_id")
    private int preId;


}
