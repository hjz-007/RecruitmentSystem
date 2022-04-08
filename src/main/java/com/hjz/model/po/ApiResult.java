package com.hjz.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 通用API响应实体
 * @author: hlx 2018-08-14
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "通用API响应体")
public class ApiResult<T> {

    @ApiModelProperty(value = "响应码即code", example = "0")
    private int status;

    @ApiModelProperty(value = "响应的提示信息", example = "success")
    private String text;

    @ApiModelProperty(value = "响应的数据", example = "{\"id\":\"5\"}")
    private T data;

}

