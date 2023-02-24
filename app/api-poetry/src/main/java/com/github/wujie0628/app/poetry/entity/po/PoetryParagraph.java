package com.github.wujie0628.app.poetry.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wujie
 * @since 2023-02-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("poetry_paragraph")
@ApiModel(value="PoetryParagraph对象", description="")
public class PoetryParagraph implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "所属诗词id")
    private Integer poetryInfoId;

    @ApiModelProperty(value = "句子内容")
    private String sentence;

    @ApiModelProperty(value = "上一句的id")
    private Integer preId;


}
