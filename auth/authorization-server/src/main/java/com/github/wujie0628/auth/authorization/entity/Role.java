package com.github.wujie0628.auth.authorization.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false )
@NoArgsConstructor
public class Role {

    private static final long serialVersionUID = 1L;

    private String code;

    private String name;

    private String description;
}
