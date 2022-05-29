package com.hjz.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description: 通用API响应实体
 * @author: hlx 2018-08-14
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "通用API响应体")
public class ApiResult<T> implements Serializable {

    @ApiModelProperty(value = "响应码即code", example = "400")
    private int code;

    @ApiModelProperty(value = "响应是否成功", example = "true")
    private boolean successful;

    @ApiModelProperty(value = "响应的提示信息", example = "success")
    private String message;

    @ApiModelProperty(value = "响应的数据", example = "{\"id\":\"5\"}")
    private T data;

    public static <T> ApiResult<T> ok(T data){
        return new ApiResult<>(200,true,"请求成功", data);
    }

    public static <T> ApiResult<T> failed(int code,String message){
        return new ApiResult<>(code,false,message,null);
    }
}

